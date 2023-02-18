package com.devcommop.joaquin.seceleaderboard.presentation.contest

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcommop.joaquin.seceleaderboard.common.Constants
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

private const val TAG = "##@@ContestVM"

@HiltViewModel
class ContestViewModel @Inject constructor(
    private val appUseCases: AppUseCases,
    savedStateHandle: SavedStateHandle//hilt will automatically give this
) : ViewModel() {

    //TODO: Use job to cancel old flows

    private val _state = mutableStateOf(ContestState())
    val state: State<ContestState> = _state
    private var getPartiesScoreJob: Job? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val event = _eventFlow.asSharedFlow()

    init {
        Log.d(TAG, "I am initialised")
        savedStateHandle.get<String>(Constants.PARAM_CONTEST_ID)?.let{ contestId ->
            _state.value.contestId = contestId
            getPartiesScore(contestId = contestId)
        }
        //getPartiesScore()
    }

    private fun getPartiesScore(contestId: String) {
        //getPartiesScoreJob?.cancel()
        //        savedStateHandle.get<String>("contestId") ?.let { contestId ->
        viewModelScope.launch {
            var cfHandles = "empty"
            appUseCases.getCfHanlesUseCase(docId = "ece_2021").collectLatest { res ->
                when (res) {
                    is Resource.Success -> {
                        Log.d(TAG, "res: ${res}")
                        cfHandles = res.data.toString()
                        val options = mapOf("contestId" to contestId, "handles" to cfHandles)
                        appUseCases.getPartiesScoreUseCase(options = options).collectLatest { it ->
                            when (it) {
                                is Resource.Success -> {
                                    _state.value = state.value.copy(
                                        parties = it.data?: emptyList(),
                                        currentStatus = "SUCCESS"
                                    )
                                    Log.d(TAG, it.data.toString())
                                }
                                is Resource.Loading -> {
                                    _state.value = state.value.copy(currentStatus = "LOADING")
                                    Log.d(TAG, "LOADING")
                                }
                                is Resource.Error -> {
                                    _state.value = state.value.copy(currentStatus = it.message?: "handles score fetch failed due to unknown error")
                                    Log.d(TAG, "ERROR: ${it.message}")
                                }
                            }
                        }
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(currentStatus = res.message?: "LOADING")
                        Log.d(TAG, "res: ${res}")
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(currentStatus = "ERROR")//todo: add message to this error
                        Log.d(TAG, "res: ${res}")
                    }
                }
            }
        }
    }
}
//}
