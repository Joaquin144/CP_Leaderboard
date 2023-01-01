package com.devcommop.joaquin.seceleaderboard.domain.repository

import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore

interface CFRepository {

    suspend fun getPartiesScore(options: Map<String,String>): PartiesScore

    suspend fun getCfHandles(docId: String): String

}