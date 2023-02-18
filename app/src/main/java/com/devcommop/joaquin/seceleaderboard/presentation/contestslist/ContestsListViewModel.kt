package com.devcommop.joaquin.seceleaderboard.presentation.contestslist

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


private const val TAG = "##@@ContestsListVM"

@HiltViewModel
class ContestsListViewModel @Inject constructor(
    private val appUseCases: AppUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    //TODO: Use job to cancel old flows
    private val _state = mutableStateOf(ContestsListState())
    val state: State<ContestsListState> = _state
    private var getPartiesScoreJob: Job? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val event = _eventFlow.asSharedFlow()

    init {
        Log.d(TAG, "I am initialised")
        getContestsList()
        getUpComingContestList()
    }

    private fun getContestsList() {
        viewModelScope.launch {
            appUseCases.getContestsListUseCase(docId = "ece_2021").collectLatest { res ->
                when (res) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            pastContestsList = res.data!!.split(";").map { contest ->
                                contest.toInt()
                            }.sorted().map { it -> it.toString() })
                    }
                    is Resource.Loading -> {
                        //todo: notify loading through event
                    }
                    is Resource.Error -> {
                        //todo: notify error through event
                    }
                }
            }
        }
    }

    private fun getUpComingContestList() {
        viewModelScope.launch {
            appUseCases.getUpcomingContestsListUseCase().collectLatest { res ->
                when (res) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(upcomingContestsList = res.data!!)
                    }
                    is Resource.Loading -> {
                        //todo: notify loading through event
                    }
                    is Resource.Error -> {
                        //todo: notify error through event
                    }
                }
            }
        }
    }
}
