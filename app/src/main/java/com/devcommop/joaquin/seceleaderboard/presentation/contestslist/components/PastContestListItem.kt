package com.devcommop.joaquin.seceleaderboard.presentation.contestslist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devcommop.joaquin.seceleaderboard.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PastContestListItem(
    contestId: String,
    onItemClick: (String) -> Unit
) {
    val paddingModifier = Modifier.padding(10.dp)
    Card(
        elevation = 10.dp,
        modifier = paddingModifier
            .shadow(8.dp),
        onClick = { onItemClick(contestId) }//todo: Try combinedClickable --> onDoubleClick & onLongClick
        //backgroundColor = Color((Math.random() * 16777215).toInt())
    ) {
        Column(
            modifier = paddingModifier,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Codeforces",
                fontFamily = FontFamily(Font(R.font.poppins)),
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Text(text = contestId)
        }
    }
}