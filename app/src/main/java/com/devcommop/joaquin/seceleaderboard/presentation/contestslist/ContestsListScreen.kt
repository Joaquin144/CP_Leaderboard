package com.devcommop.joaquin.seceleaderboard.presentation.contestslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devcommop.joaquin.seceleaderboard.R
import com.devcommop.joaquin.seceleaderboard.presentation.common.HeaderText
import com.devcommop.joaquin.seceleaderboard.presentation.contestslist.components.ContestListItem
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.SecondaryColor

@Composable
fun ContestsListScreen(
    navController: NavController,
    viewModel: ContestsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Column() {
        HeaderText(text = "Codeforces Contests")
        PastContestsUI(state = state)
        UpcomingContestsUI()
    }
}

@Composable
fun PastContestsUI(state: ContestsListState) {
    Text(
        text = "Past Contests",
        fontFamily = FontFamily(Font(R.font.poppins)),
        color = SecondaryColor,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 18.dp)
    )
    LazyRow(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        items(state.list){contestId ->
            ContestListItem(contestId = contestId)
        }
    }
}

@Composable
fun UpcomingContestsUI() {

}
