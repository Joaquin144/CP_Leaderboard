package com.devcommop.joaquin.seceleaderboard.presentation.scoreboard

import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Row

data class ScoreboardState(
    val rankings: HashMap<String, Int> = hashMapOf(),
    var currentStatus: String = "LOADING"//[check] whether if 'var' is a good practice or not here ?
)
