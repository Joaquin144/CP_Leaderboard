package com.devcommop.joaquin.seceleaderboard.data.remote.custom

import com.devcommop.joaquin.seceleaderboard.common.Constants
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Row
import com.devcommop.joaquin.seceleaderboard.domain.util.CfScoreCalculator
import com.devcommop.joaquin.seceleaderboard.domain.util.SortHashMap

data class ScoreboardResult(
//    var list: List<ScoreboardRow>? = null,
    //val scores: HashMap<String, List<Int>> = hashMapOf<String, List<Int>>(),
    var totalScores: HashMap<String, Int> = hashMapOf<String, Int>(),
    var exception: Exception? = null
){
    fun sortTotalScores(){
        totalScores = SortHashMap.sortByValueDescending(totalScores)
    }

    fun updateScore(rows: List<Row>) {
        for (row in rows) {
            val handleName = row.party.members[Constants.DEFAULT_MEMBER_IDX].handle
            val handleRank = row.rank
            val handleScore = CfScoreCalculator.getScore(handleRank)

            if (totalScores[handleName] == null)
                totalScores[handleName] = 0
            totalScores[handleName] = totalScores[handleName]!! + handleScore
        }
    }
}
