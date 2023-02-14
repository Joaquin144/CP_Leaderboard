package com.devcommop.joaquin.seceleaderboard.domain.repository

import com.devcommop.joaquin.seceleaderboard.data.remote.custom.ScoreboardResult
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore

/**
 * @param [docId] is the id of Firestore document where data has been saved
 */
interface CFRepository {

    suspend fun getPartiesScore(options: Map<String,String>): PartiesScore

    suspend fun getCfHandles(docId: String): String

    suspend fun getContests(docId: String): String

    suspend fun getScoreboard(docId: String): ScoreboardResult

}