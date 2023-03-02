package com.devcommop.joaquin.seceleaderboard.presentation.contestslist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devcommop.joaquin.seceleaderboard.R
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Contest
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.SecondaryColor

@Composable
fun UpcomingContestListItem(contest: Contest) {
    Spacer(modifier = Modifier.height(12.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Text(
            text = contest.name!!,
            fontFamily = FontFamily(Font(R.font.poppins)),
            color = SecondaryColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = contest.getStartTime(),
                fontFamily = FontFamily(Font(R.font.poppins)),
                color = SecondaryColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = contest.getDuration(),
                fontFamily = FontFamily(Font(R.font.poppins)),
                color = Color.Gray,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.offset(y = (-4).dp)
            )
        }
    }
}