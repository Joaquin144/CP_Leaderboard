package com.devcommop.joaquin.seceleaderboard.presentation.contest.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ContestScreenRowItemHeading() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Serial")
        Text(text = "Handle")
        Text(text = "Rank")
        Text(text = "Points")
        Text(text = "Penalty")

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContestScreenRowItemHeading() {
    ContestScreenRowItemHeading()
}