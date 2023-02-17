package com.devcommop.joaquin.seceleaderboard.presentation.contestslist

import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Contest

data class ContestsListState(
    val pastContestsList: List<String> = listOf(),
    val upcomingContestsList: List<Contest> = listOf()
)
