package com.devcommop.joaquin.seceleaderboard.presentation.scoreboard

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcommop.joaquin.seceleaderboard.domain.use_cases.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "##@@ScoreboardVM"
@HiltViewModel
class ScoreboardViewModel @Inject constructor(
    private val useCases: AppUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    init {
        Log.d(TAG, "I am initialised")
        getOverallRankings()
    }

    private fun getOverallRankings(){
        viewModelScope.launch {

        }
    }
}