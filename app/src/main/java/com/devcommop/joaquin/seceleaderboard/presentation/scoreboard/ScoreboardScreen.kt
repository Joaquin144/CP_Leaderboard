package com.devcommop.joaquin.seceleaderboard.presentation.scoreboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devcommop.joaquin.seceleaderboard.presentation.common.AnimatedShimmer
import com.devcommop.joaquin.seceleaderboard.presentation.common.HeaderText
import com.devcommop.joaquin.seceleaderboard.presentation.scoreboard.components.HandleScoreItem

@Composable
fun ScoreboardScreen(
    navController: NavController,
    viewModel: ScoreboardViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    if (state.currentStatus != "SUCCESS") {
        Column {
            repeat(15) {
                AnimatedShimmer()
            }
        }
    } else {
        Scaffold(
            scaffoldState = scaffoldState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                HeaderText(text = "Scoreboard")
                RankingUI(state)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RankingUI(state: ScoreboardState) {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        var idx = 1
        state.rankings.forEach {
            //TODO: give onClick functionality to navigate to Contestant Screen
            HandleScoreItem(index = idx++, handleName = it.key, score = it.value, onClick = {})
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewScoreboardScreen() {
//    ScoreboardScreen()
//}