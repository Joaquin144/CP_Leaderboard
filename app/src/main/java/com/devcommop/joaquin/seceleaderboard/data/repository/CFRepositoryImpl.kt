package com.devcommop.joaquin.seceleaderboard.data.repository

import android.util.Log
import com.devcommop.joaquin.seceleaderboard.data.remote.CFApi
import com.devcommop.joaquin.seceleaderboard.data.remote.custom.ScoreboardResult
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.FirebaseUserEntity
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow
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
        val result = ScoreboardResult()
        try{
            runBlocking {
                val cfHandles = getCfHandles(docId = docId)
                val contests = getContests(docId = docId).split(";")
                Log.d(TAG, "cfHandles in runBlocking: $cfHandles")
                for(contest in contests){
                    val options: HashMap<String,String> = hashMapOf()
                    options["contestId"] = contest
                    options["handles"] = cfHandles
                    val currScore = getPartiesScore(options = options)//E! -> again runBlocking??
                    result.scores
                }
            }
        }catch (exception: Exception) {
            result.exception = exception
        }
        return result
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

