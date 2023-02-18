package com.devcommop.joaquin.seceleaderboard.presentation.scoreboard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcommop.joaquin.seceleaderboard.common.Resource
import com.devcommop.joaquin.seceleaderboard.domain.use_cases.AppUseCases
import com.devcommop.joaquin.seceleaderboard.presentation.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "##@@ScoreboardVM"

@HiltViewModel
class ScoreboardViewModel @Inject constructor(
    private val appUseCases: AppUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    //TODO: Use job to cancel old flows
    private val _state = mutableStateOf(ScoreboardState())
    val state: State<ScoreboardState> = _state
    private var getPartiesScoreJob: Job? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val event = _eventFlow.asSharedFlow()

    init {
        Log.d(TAG, "I am initialised")
        getOverallRankings()
    }

    private fun getOverallRankings() {
        viewModelScope.launch {
            appUseCases.getScoreboardUseCase(docId = "ece_2021").collectLatest { res ->
                when (res) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(rankings = res.data!!.totalScores,
                        currentStatus = "SUCCESS")
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(currentStatus = res.message ?: "LOADING")
                        Log.d(TAG, "res: ${res}")
                    }
                    is Resource.Error -> {
                        _state.value =
                            state.value.copy(currentStatus = "ERROR")//todo: add message to this error
                        Log.d(TAG, "res: ${res}")
                    }
                }
            }
        }
    }
}