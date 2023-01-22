package com.devcommop.joaquin.seceleaderboard.data.remote.custom

data class ScoreboardResult(
//    var list: List<ScoreboardRow>? = null,
    val scores: HashMap<String, List<Int>> = hashMapOf<String, List<Int>>(),
    val totalScores: HashMap<String, Int> = hashMapOf<String, Int>(),
    var exception: Exception? = null
)
