package com.devcommop.joaquin.seceleaderboard.data.remote.dto

import com.devcommop.joaquin.seceleaderboard.domain.util.CfScoreCalculator

data class Row(
    val party: Party,
    val penalty: Int,
    val points: Int,
    val rank: Int
)//{ todo: Make this work
//    val score = CfScoreCalculator.getScore(rank)  --> this is giving zero
//}