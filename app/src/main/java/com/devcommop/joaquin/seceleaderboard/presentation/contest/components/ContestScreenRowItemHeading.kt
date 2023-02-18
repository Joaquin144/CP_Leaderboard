package com.devcommop.joaquin.seceleaderboard.presentation.contest.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devcommop.joaquin.seceleaderboard.R
import com.devcommop.joaquin.seceleaderboard.presentation.common.HeaderText
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.SecondaryColor

@Composable
fun ContestScreenRowItemHeading() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ContestScreenSubHeading(text = "Serial")
        ContestScreenSubHeading(text = "Handle")
        ContestScreenSubHeading(text = "Rank")
        ContestScreenSubHeading(text = "Score")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContestScreenRowItemHeading() {
    ContestScreenRowItemHeading()
}

@Composable
fun ContestScreenSubHeading(text: String){
    Text(
        text = text,
        fontFamily = FontFamily(Font(R.font.poppins)),
        color = SecondaryColor,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth(),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    )
}