package com.devcommop.joaquin.seceleaderboard.presentation.contest.components

import androidx.compose.foundation.clickable
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
fun ContestScreenRowItem(
    serial: Int = 1,
    handleName: String = "joaquin144",
    handleOnClick: () -> Unit = {},
    handleRank: Int = 1,
    handlePoints: Int = 0,
    handlePenalty: Int = 0
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = serial.toString())
//        Text(
//            modifier = Modifier.
//                clickable { handleOnClick },
//            text = handleName
//        )
        CfHandleBox(handleName = handleName, onHandleClick = handleOnClick)
        Text(text = handleRank.toString())
        Text(text = handlePoints.toString())
        Text(text = handlePenalty.toString())

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContestScreenRowItem() {
    ContestScreenRowItem()
}