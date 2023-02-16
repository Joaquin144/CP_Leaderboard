package com.devcommop.joaquin.seceleaderboard.data.remote.custom

import com.devcommop.joaquin.seceleaderboard.domain.util.SortHashMap

data class ScoreboardResult(
//    var list: List<ScoreboardRow>? = null,
    val scores: HashMap<String, List<Int>> = hashMapOf<String, List<Int>>(),
    var totalScores: HashMap<String, Int> = hashMapOf<String, Int>(),
    var exception: Exception? = null
){
    fun sortTotalScores(){
        totalScores = SortHashMap.sortByValueDescending(totalScores)
    }
}
