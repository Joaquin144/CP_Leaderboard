package com.devcommop.joaquin.seceleaderboard.presentation.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devcommop.joaquin.seceleaderboard.R
import com.devcommop.joaquin.seceleaderboard.presentation.common.HeaderText
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.LightPrimaryColor
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.SecondaryColor
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.Shapes

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    Column() {
        HeaderText(text = "Settings")
        GeneralOptionsUI()
        SupportOptionsUI()
    }
}

@ExperimentalMaterialApi
@Composable
fun GeneralOptionsUI() {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "General",
            fontFamily = FontFamily(Font(R.font.poppins)),
            color = SecondaryColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        GeneralSettingItem(
            icon = R.drawable.ic_rounded_notification,
            mainText = "Notifications",
            subText = "Customize notifications",
            onClick = {}
        )
        GeneralSettingItem(
            icon = R.drawable.ic_more,
            mainText = "More customization",
            subText = "Customize it more to fit your usage",
            onClick = {}
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun GeneralSettingItem(icon: Int, mainText: String, subText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        backgroundColor = Color.White,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(shape = Shapes.medium)
                        .background(LightPrimaryColor)
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Text(
                        text = mainText,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        color = SecondaryColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = subText,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        color = Color.Gray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.offset(y = (-4).dp)
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )

        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SupportOptionsUI() {
    val context = LocalContext.current
    val gitIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Joaquin144/CP_Leaderboard")) }
    val aboutIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://demo-electropix.netlify.app/")) }

    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Support",
            fontFamily = FontFamily(Font(R.font.poppins)),
            color = SecondaryColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        SupportItem(
            icon = R.drawable.ic_whatsapp,
            mainText = "Contact",
            onClick = {}
        )
        SupportItem(
            icon = R.drawable.ic_feedback,
            mainText = "Feedback",
            onClick = {}
        )
        SupportItem(
            icon = R.drawable.ic_privacy_policy,
            mainText = "Privacy Policy",
            onClick = {}
        )
        SupportItem(
            icon = R.drawable.ic_about,
            mainText = "About",
            onClick = { context.startActivity(aboutIntent) }
        )
        SupportItem(
            icon = R.drawable.ic_eye_open,
            mainText = "Code",
            onClick = { context.startActivity(gitIntent) }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun SupportItem(icon: Int, mainText: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        backgroundColor = Color.White,
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
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = mainText,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = SecondaryColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )

        }
    }
}