package com.devcommop.joaquin.seceleaderboard.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devcommop.joaquin.seceleaderboard.presentation.contest.ContestScreen
import com.devcommop.joaquin.seceleaderboard.presentation.scoreboard.ScoreboardScreen
import com.devcommop.joaquin.seceleaderboard.presentation.settings.SettingsScreen
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.BackgroundColor
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.SECELeaderboardTheme
import com.devcommop.joaquin.seceleaderboard.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SECELeaderboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundColor
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ScoreboardScreen.route
                    ){
                        composable(
                            route = Screen.ScoreboardScreen.route
                        ){
                            ScoreboardScreen(navController = navController)
                        }
                        composable(
                            route = Screen.ContestScreen.route// + "?contestId={contestId}",
//                            arguments = listOf(
//                                navArgument(
//                                    name = "contestId"
//                                ){
//                                    type = NavType.StringType
//                                    defaultValue = "1767"
//                                }
//                            )
                        ){
                            ContestScreen(navController = navController)
                        }
                        composable(
                            route = Screen.SettingsScreen.route
                        ){
                            SettingsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SECELeaderboardTheme {
        Greeting("Android")
    }
}