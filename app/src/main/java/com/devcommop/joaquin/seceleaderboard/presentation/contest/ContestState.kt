package com.devcommop.joaquin.seceleaderboard.presentation.contest

import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Row

//TODO: Add ordering (ascending/descending wrt alphabet, score, rank etc.)
data class ContestState(
    val parties: List<Row> = emptyList(),
    var currentStatus: String = "LOADING",//[check] -> is var a good practice
    var contestId: String = "Contest not found"
)