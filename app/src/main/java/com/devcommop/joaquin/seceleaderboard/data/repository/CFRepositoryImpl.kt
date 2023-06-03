package com.devcommop.joaquin.seceleaderboard.data.repository

import android.util.Log
import com.devcommop.joaquin.seceleaderboard.common.Constants
import com.devcommop.joaquin.seceleaderboard.common.CustomException
import com.devcommop.joaquin.seceleaderboard.data.cache.CachedContestsDb
import com.devcommop.joaquin.seceleaderboard.data.cache.ContestsDao
import com.devcommop.joaquin.seceleaderboard.data.remote.CFApi
import com.devcommop.joaquin.seceleaderboard.data.remote.custom.ScoreboardResult
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Contest
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.FirebaseUserEntity
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import com.devcommop.joaquin.seceleaderboard.domain.util.CfScoreCalculator
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val TAG = "##@@CFRepoImpl"

class CFRepositoryImpl @Inject constructor(
    private val api: CFApi,
    private val contestsDao: ContestsDao
) : CFRepository {

    private val firestore = FirebaseFirestore.getInstance()

    private suspend fun getCachedPartiesScore(options: Map<String, String>): PartiesScore? {
        //todo: If cfHandles are changed then cached score should become invalid
        return contestsDao.getContestById(id = options["contestId"]!!.toInt())
    }

    override suspend fun getPartiesScore(options: Map<String, String>): PartiesScore {
        val temp = getCachedPartiesScore(options = options)
        if (temp != null)
            return temp
        val onlinePartyScore = api.getPartiesScore(options = options)
        if (onlinePartyScore.status == Constants.CF_API_SUCCESS_STATUS) {
            contestsDao.insertContest(
                PartiesScore(
                    result = onlinePartyScore.result,
                    status = onlinePartyScore.status,
                    id = options["contestId"]!!.toInt()
                )
            )
            Log.d(TAG, "Caching contest: ${options["contestId"]!!.toInt()}")
        }
        return onlinePartyScore
    }

    /**
     * This function will get all Codeforces handles from Firebase Firestore
     */
    override suspend fun getCfHandles(docId: String): String {
        try {
            val userEntity = firestore.collection(Constants.USER_COLLECTION).document(docId).get().await()
                .toObject(FirebaseUserEntity::class.java)
            return userEntity?.handles ?: "-1"
        } catch (exception: Exception) {
            return "-1"
        }
    }

    /**
     * Gives the overall ranking of contestants where all contests are considered
     */
    override suspend fun getScoreboard(docId: String): ScoreboardResult {
        val scoreboardResult = ScoreboardResult()
        try {
            withContext(Dispatchers.IO) {
                val cfHandles = getCfHandles(docId = docId)
                val contests = getContests(docId = docId).split(";")
                Log.d(TAG, "contests size = ${contests.size}  ===>  $contests")
                Log.d(TAG, Thread.currentThread().toString())
                val uncachedContests = mutableListOf<String>()

                //Aim: 1st get scores of contests which are cached
                async {
                    for (contest in contests) {
                        val options = hashMapOf("contestId" to contest, "handles" to cfHandles)
                        val contestScore = getCachedPartiesScore(options = options)
                        if (contestScore == null) {
                            uncachedContests.add(contest)
                        } else {
                            scoreboardResult.updateScore(contestScore.result.rows)
                        }
                    }
                }.await()

                //Aim: 2nd call CF Api to get scores of contests not yet cached on our device
                for (contest in uncachedContests) {
                    val options = hashMapOf("contestId" to contest, "handles" to cfHandles)
                    Log.d(TAG, "Calling CF Api for contest: $contest")
                    lateinit var contestScore: PartiesScore
                    /*todo: [Important] Avoid using this delay.
                    Currently CF blocks too many requests too quickly. So to avoid too many requests in less time we are using a delay of 1500ms. But this is making app experience bad.
                     */
                    delay(1500)
                    async {
                        Log.d(TAG, Thread.currentThread().toString())
                        contestScore = getPartiesScore(options = options)
                        if (contestScore.status == Constants.CF_API_SUCCESS_STATUS) {
                            Log.d(TAG, "Success $contest")
                            scoreboardResult.updateScore(contestScore.result.rows)
                        } else {
                            throw (CustomException(message = "Failed to fetch contest: Codeforces $contest"))
                        }
                    }.await()
                }
            }
            Log.d(TAG, "Everything went well")
            scoreboardResult.sortTotalScores()
            return scoreboardResult
        } catch (exception: Exception) {
            Log.d(TAG, "Something bad happened : ${exception.message} ;; ${exception.localizedMessage}")
            scoreboardResult.exception = exception
            scoreboardResult.totalScores = hashMapOf()//Aim: reset the totalScores
            return scoreboardResult
        }
    }

    /**
     * Returns String of contests separated by a semicolon (;)
     */
    override suspend fun getContests(docId: String): String {
        ////val cc = CoroutinePractice()
        try {
            val userEntity = firestore.collection("USERS").document(docId).get().await()
                .toObject(FirebaseUserEntity::class.java)
            return userEntity?.contests ?: "-1"
        } catch (exception: Exception) {
            return "-1"
        }
    }

    /**
     * Fetches the future contests of Codeforces
     */
    override suspend fun getUpcomingContestsList(): List<Contest> {
        try {
            val list = api.getUpcomingContestsList().result.filter { contest ->
                contest.phase == "BEFORE"
                //contest.phase == "BEFORE" && (contest.relativeTimeSeconds.let { time -> abs(time) <= 24 * 7 * 60 * 60 })
            }
            //Log.d(TAG, "Upcoming Contests: $list")
            return list.reversed()
        } catch (exception: Exception) {
            return emptyList()
        }
    }
}

//override suspend fun getScoreboard(docId: String): ScoreboardResult {
//    val scoreboardResult = ScoreboardResult()
//    try {
//        runBlocking {
//            val cfHandles = getCfHandles(docId = docId)
//            val contests = getContests(docId = docId).split(";")
//            Log.d(TAG, "contests size = ${contests.size}  ===>  $contests")
//            Log.d(TAG, "cfHandles in runBlocking: $cfHandles")
//            for (contest in contests) {
//                Log.d(TAG, "doing contest :  $contest")
//                val options: HashMap<String, String> = hashMapOf()
//                options["contestId"] = contest
//                options["handles"] = cfHandles
//                lateinit var contestScore: PartiesScore
//                val contestScoreJob =
//                    launch { contestScore = getPartiesScore(options = options) }
//                ////val contestScore = getPartiesScore(options = options)//E! -> again runBlocking??
//                contestScoreJob.join()
//                if (contestScore.status == Constants.CF_API_SUCCESS_STATUS) {
//                    Log.d(TAG, "Success $contest")
//                    for (row in contestScore.result.rows) {
//                        val handleName = row.party.members[Constants.DEFAULT_MEMBER_IDX].handle
//                        val handleRank = row.rank
//                        val handleScore = CfScoreCalculator.getScore(handleRank)
//                        if (scoreboardResult.totalScores[handleName] == null)
//                            scoreboardResult.totalScores[handleName] = 0
//                        scoreboardResult.totalScores[handleName] =
//                            scoreboardResult.totalScores[handleName]!! + handleScore
//                    }
//                } else {
//                    Log.d(TAG, "Something bad happened in contest: $contest")
//                    //throw CustomException(message = contestScore.comment)
//                }
//                delay(150)
//            }
//        }
//    } catch (exception: Exception) {
//        Log.d(TAG, "Something bad happened : ${exception.message}")
//        scoreboardResult.exception = exception
//    }
//    Log.d(TAG, scoreboardResult.totalScores.toSortedMap().toString())
//    scoreboardResult.sortTotalScores()
//    return scoreboardResult
//}

