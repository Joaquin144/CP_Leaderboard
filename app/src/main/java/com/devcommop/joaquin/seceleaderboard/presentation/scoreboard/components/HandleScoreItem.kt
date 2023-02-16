package com.devcommop.joaquin.seceleaderboard.presentation.scoreboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devcommop.joaquin.seceleaderboard.R
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.LightPrimaryColor
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.PrimaryColor
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.SecondaryColor
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.Shapes

@ExperimentalMaterialApi
@Composable
fun HandleScoreItem(
    index: Int,
    handleName: String,
    score: Int,
    onClick: () -> Unit
) {
    Card(
        onClick = { onClick() },
        backgroundColor = PrimaryColor,//Color.White,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(shape = Shapes.medium)
                        .background(LightPrimaryColor)
                ) {
                    Text(
                        text = index.toString(),
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = handleName,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = SecondaryColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = score.toString(),
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
