package com.devcommop.joaquin.seceleaderboard.data.repository

import android.util.Log
import com.devcommop.joaquin.seceleaderboard.common.Constants
import com.devcommop.joaquin.seceleaderboard.common.CustomException
import com.devcommop.joaquin.seceleaderboard.data.remote.CFApi
import com.devcommop.joaquin.seceleaderboard.data.remote.custom.ScoreboardResult
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Contest
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.FirebaseUserEntity
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import com.devcommop.joaquin.seceleaderboard.domain.util.CfScoreCalculator
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.math.abs

private const val TAG = "##@@CFRepoImpl"

class CFRepositoryImpl @Inject constructor(
    private val api: CFApi
) : CFRepository {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun getPartiesScore(options: Map<String, String>): PartiesScore {
        return api.getPartiesScore(options = options)
    }

    override suspend fun getCfHandles(docId: String): String {
        try {
            val userEntity = firestore.collection("USERS").document(docId).get().await()
                .toObject(FirebaseUserEntity::class.java)
            return userEntity?.handles ?: "-1"
        } catch (exception: Exception) {
            return "-1"
        }
    }

    override suspend fun getScoreboard(docId: String): ScoreboardResult {
        val scoreboardResult = ScoreboardResult()
        try {
            runBlocking {
                val cfHandles = getCfHandles(docId = docId)
                val contests = getContests(docId = docId).split(";")
                Log.d(TAG, "contests size = ${contests.size}  ===>  $contests")
                Log.d(TAG, "cfHandles in runBlocking: $cfHandles")
                for (contest in contests) {
                    Log.d(TAG, "doing contest :  $contest")
                    val options: HashMap<String, String> = hashMapOf()
                    options["contestId"] = contest
                    options["handles"] = cfHandles
                    lateinit var contestScore: PartiesScore
                    val contestScoreJob =
                        launch { contestScore = getPartiesScore(options = options) }
                    ////val contestScore = getPartiesScore(options = options)//E! -> again runBlocking??
                    contestScoreJob.join()
                    if (contestScore.status == Constants.CF_API_SUCCESS_STATUS) {
                        Log.d(TAG, "Success $contest")
                        for (row in contestScore.result.rows) {
                            val handleName = row.party.members[Constants.DEFAULT_MEMBER_IDX].handle
                            val handleRank = row.rank
                            val handleScore = CfScoreCalculator.getScore(handleRank)
                            if (scoreboardResult.totalScores[handleName] == null)
                                scoreboardResult.totalScores[handleName] = 0
                            scoreboardResult.totalScores[handleName] =
                                scoreboardResult.totalScores[handleName]!! + handleScore
                        }
                    } else {
                        Log.d(TAG, "Something bad happened in contest: $contest")
                        //throw CustomException(message = contestScore.comment)
                    }
                    delay(150)
                }
            }
        } catch (exception: Exception) {
            Log.d(TAG, "Something bad happened : ${exception.message}")
            scoreboardResult.exception = exception
        }
        Log.d(TAG, scoreboardResult.totalScores.toSortedMap().toString())
        scoreboardResult.sortTotalScores()
        return scoreboardResult
    }

    override suspend fun getContests(docId: String): String {
        try {
            val userEntity = firestore.collection("USERS").document(docId).get().await()
                .toObject(FirebaseUserEntity::class.java)
            return userEntity?.contests ?: "-1"
        } catch (exception: Exception) {
            return "-1"
        }
    }

    override suspend fun getUpcomingContestsList(): List<Contest> {
        try {
            val list = api.getUpcomingContestsList().result.filter { contest ->
                contest.phase == "BEFORE"
                //contest.phase == "BEFORE" && (contest.relativeTimeSeconds.let { time -> abs(time) <= 24 * 7 * 60 * 60 })
            }
            Log.d(TAG, "Upcoming Contests: $list")
            return list.reversed()
        } catch (exception: Exception) {
            return emptyList()
        }
    }

}

