package com.devcommop.joaquin.seceleaderboard.presentation.contest

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devcommop.joaquin.seceleaderboard.domain.util.CfScoreCalculator
import com.devcommop.joaquin.seceleaderboard.presentation.contest.components.ContestScreenRowItem
import com.devcommop.joaquin.seceleaderboard.presentation.contest.components.ContestScreenRowItemHeading
import com.devcommop.joaquin.seceleaderboard.presentation.contest.components.ScreenHeading
import com.devcommop.joaquin.seceleaderboard.presentation.common.AnimatedShimmer

private const val TAG = "##@@ContestScreen"

/**
 * This screen will show all the rankings related to one specific contest only.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContestScreen(
    navController: NavController,
    viewModel: ContestViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ScreenHeading(text = state.contestId)
            if (state.currentStatus == "LOADING") {
                repeat(15) {
                    AnimatedShimmer()
                }
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                ContestScreenRowItemHeading()
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(state.parties) { currIndex, party ->
                        ContestScreenRowItem(
                            serial = currIndex + 1,
                            handleName = party.party.members[0].handle,
                            handleOnClick = { Log.d(TAG, "Handle clicked at index: $currIndex") },
                            handleRank = party.rank,
                            handleScore = CfScoreCalculator.getScore(party.rank)
                        )
                    }
                }
            }
        }
    }
}

/*
floatingActionButton = {
FloatingActionButton(
onClick = { },
backgroundColor = MaterialTheme.colors.primary
) {
Icon(Icons.Default.Add, contentDescription = "Hi")
}
},
*/