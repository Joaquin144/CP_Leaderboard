package com.devcommop.joaquin.seceleaderboard.data.repository

import android.util.Log
import com.devcommop.joaquin.seceleaderboard.common.Constants
import com.devcommop.joaquin.seceleaderboard.common.CustomException
import com.devcommop.joaquin.seceleaderboard.data.remote.CFApi
import com.devcommop.joaquin.seceleaderboard.data.remote.custom.ScoreboardResult
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.FirebaseUserEntity
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val TAG = "##@@CFRepoImpl"

class CFRepositoryImpl @Inject constructor(
    private val api: CFApi
): CFRepository {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun getPartiesScore(options: Map<String, String>): PartiesScore {
        return api.getPartiesScore(options = options)
    }

    override suspend fun getCfHandles(docId: String): String{
        try{
            val userEntity = firestore.collection("USERS").document(docId).get().await().toObject(FirebaseUserEntity::class.java)
            return userEntity?.handles?:"-1"
        }catch (exception: Exception) {
            return "-1"
        }
    }

    override suspend fun getScoreboard(docId: String, removeOptions: Map<String, String>): ScoreboardResult {
        val scoreboardResult = ScoreboardResult()
        try{
            runBlocking {
                val cfHandles = getCfHandles(docId = docId)
                val contests = getContests(docId = docId).split(";")
                Log.d(TAG, "cfHandles in runBlocking: $cfHandles")
                for(contest in contests){
                    val options: HashMap<String,String> = hashMapOf()
                    options["contestId"] = contest
                    options["handles"] = cfHandles
                    val contestScore = getPartiesScore(options = options)//E! -> again runBlocking??
                    if(contestScore.status == Constants.CF_API_SUCCESS_STATUS){
                        for(row in contestScore.result.rows){
                            val handleName = row.party.members[Constants.DEFAULT_MEMBER_IDX].handle
                            val handleScore = row.points
                            if(scoreboardResult.totalScores[handleName] == null)
                                scoreboardResult.totalScores[handleName] = 0
                            scoreboardResult.totalScores[handleName] = scoreboardResult.totalScores[handleName]!! + handleScore
                        }
                    }else{
                        throw CustomException(message = contestScore.comment)
                    }
//                    scoreboardResult.scores
                }
            }
        }catch (exception: Exception) {
            scoreboardResult.exception = exception
        }
        Log.d(TAG, scoreboardResult.totalScores.toString())
        return scoreboardResult
    }

    override suspend fun getContests(docId: String): String {
        try{
            val userEntity = firestore.collection("USERS").document(docId).get().await().toObject(FirebaseUserEntity::class.java)
            return userEntity?.contests?:"-1"
        }catch (exception: Exception) {
            return "-1"
        }
    }

}

