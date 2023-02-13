package com.devcommop.joaquin.seceleaderboard.presentation.scoreboard

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ScoreboardScreen(
    navController: NavController,
    viewModel: ScoreboardViewModel = hiltViewModel()
) {
    Text(
        text = "Scoreboard Screen"
    )
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewScoreboardScreen() {
//    ScoreboardScreen()
//}