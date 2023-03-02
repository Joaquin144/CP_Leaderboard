package com.devcommop.joaquin.seceleaderboard.presentation.scoreboard

import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Row

data class ScoreboardState(
    val rankings: HashMap<String, Int> = hashMapOf(),
    val currentStatus: CurrentStatus = CurrentStatus.Loading,//[check] whether if 'var' is a good practice or not here ?
    val errorMessage: String = "Some Unknown Error Occurred"
)

sealed class CurrentStatus{
    object Loading: CurrentStatus()
    object Success: CurrentStatus()
    object Error: CurrentStatus()
}
