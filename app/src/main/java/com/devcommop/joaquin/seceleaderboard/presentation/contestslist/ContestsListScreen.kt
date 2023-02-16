package com.devcommop.joaquin.seceleaderboard.presentation.contestslist

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devcommop.joaquin.seceleaderboard.presentation.common.HeaderText

@Composable
fun ContestsListScreen(
    navController: NavController,
    viewModel: ContestsListViewModel = hiltViewModel()
) {
    Column() {
        HeaderText(text = "Codeforces Contests")
        PastContestsUI()
        UpcomingContestsUI()
    }
}

@Composable
fun UpcomingContestsUI() {
    TODO("Not yet implemented")
}

@Composable
fun PastContestsUI() {
    TODO("Not yet implemented")
}
