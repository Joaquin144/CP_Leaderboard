package com.devcommop.joaquin.seceleaderboard.presentation.util

sealed class Screen(val route: String) {

    object SplashScreen: Screen("splash_screen")
    object OnboardingScreen: Screen("onboarding_screen")
    object LoginScreen: Screen("login_screen")
    object ScoreboardScreen: Screen("scoreboard_screen")
    object ContestScreen: Screen("contest_screen")
    object ContestantScreen: Screen("contestant_screen")
    object SettingsScreen: Screen("settings_screen")
    object ContestsListScreen: Screen("contests_list_screen")

}
