package com.devcommop.joaquin.seceleaderboard.domain.use_cases

import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import javax.inject.Inject

class GetScoreboardUseCase @Inject constructor(
    private val repository: CFRepository
) {

}