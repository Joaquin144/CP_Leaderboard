package com.devcommop.joaquin.seceleaderboard.presentation.scoreboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.devcommop.joaquin.seceleaderboard.domain.use_cases.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreboardViewModel @Inject constructor(
    private val useCases: AppUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
}