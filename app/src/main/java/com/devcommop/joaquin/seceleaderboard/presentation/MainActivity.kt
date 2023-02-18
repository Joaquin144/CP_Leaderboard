package com.devcommop.joaquin.seceleaderboard.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devcommop.joaquin.seceleaderboard.common.Constants
import com.devcommop.joaquin.seceleaderboard.presentation.contest.ContestScreen
import com.devcommop.joaquin.seceleaderboard.presentation.contestslist.ContestsListScreen
import com.devcommop.joaquin.seceleaderboard.presentation.scoreboard.ScoreboardScreen
import com.devcommop.joaquin.seceleaderboard.presentation.settings.SettingsScreen
import com.devcommop.joaquin.seceleaderboard.presentation.sidedrawer.AppBar
import com.devcommop.joaquin.seceleaderboard.presentation.sidedrawer.DrawerBody
import com.devcommop.joaquin.seceleaderboard.presentation.sidedrawer.DrawerHeader
import com.devcommop.joaquin.seceleaderboard.presentation.sidedrawer.MenuItem
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.BackgroundColor
import com.devcommop.joaquin.seceleaderboard.presentation.ui.theme.SECELeaderboardTheme
import com.devcommop.joaquin.seceleaderboard.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SECELeaderboardTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,//not to be annoying
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "contests_list_screen",
                                    title = "Contests",
                                    contentDescription = "Go to contests screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "scoreboard_screen",
                                    title = "CP Leaderboard",
                                    contentDescription = "Go to leaderboard screen",
                                    icon = Icons.Default.Home
                                ),MenuItem(
                                    id = "settings_screen",
                                    title = "Settings",
                                    contentDescription = "Go to settings screen",
                                    icon = Icons.Default.Settings
                                ),
                            ),
                            onItemClick = {
                                when(it.id){
                                    "contests_list_screen" -> {
                                        navController.navigate(route = Screen.ContestsListScreen.route) {
                                            launchSingleTop = true
                                        }
                                    }
                                    "scoreboard_screen" -> {
                                        navController.navigate(route = Screen.ScoreboardScreen.route){
                                            popUpTo(Screen.ContestsListScreen.route)
                                            launchSingleTop = true
                                        }
                                    }
                                    "settings_screen" -> {
                                        navController.navigate(route = Screen.SettingsScreen.route){
                                            popUpTo(Screen.ContestsListScreen.route)
                                            launchSingleTop = true
                                        }
                                    }
                                }
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = BackgroundColor
                ) {
                    //val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ContestsListScreen.route
                    ) {
                        composable(
                            route = Screen.ScoreboardScreen.route
                        ) {
                            ScoreboardScreen(navController = navController)
                        }
                        composable(
                            route = Screen.ContestsListScreen.route
                        ) {
                            ContestsListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.ContestScreen.route + "/{contestId}",
//                            arguments = listOf(
//                                navArgument(
//                                    name = "contestId"
//                                ){
//                                    type = NavType.StringType
//                                    defaultValue = "1767"
//                                }
//                            )
                        ) {
                            ContestScreen(navController = navController)
                        }
                        composable(
                            route = Screen.SettingsScreen.route
                        ) {
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