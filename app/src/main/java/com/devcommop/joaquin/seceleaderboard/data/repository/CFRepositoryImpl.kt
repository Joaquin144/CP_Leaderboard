package com.devcommop.joaquin.seceleaderboard.data.repository

import com.devcommop.joaquin.seceleaderboard.data.remote.CFApi
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.FirebaseUserEntity
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

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
}

