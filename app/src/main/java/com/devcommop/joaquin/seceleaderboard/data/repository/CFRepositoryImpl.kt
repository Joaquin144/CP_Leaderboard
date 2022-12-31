package com.devcommop.joaquin.seceleaderboard.data.repository

import com.devcommop.joaquin.seceleaderboard.data.remote.CFApi
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import javax.inject.Inject

class CFRepositoryImpl @Inject constructor(
    private val api: CFApi
): CFRepository {

    override suspend fun getPartiesScore(options: Map<String, String>): PartiesScore {
        return api.getPartiesScore(options = options)
    }
}