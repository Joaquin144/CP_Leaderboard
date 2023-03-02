package com.devcommop.joaquin.seceleaderboard.presentation.scoreboard

sealed class ScoreboardEvent {
    object Retry: ScoreboardEvent()
}