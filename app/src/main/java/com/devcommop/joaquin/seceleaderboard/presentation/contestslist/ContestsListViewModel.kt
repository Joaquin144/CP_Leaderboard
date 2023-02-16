package com.devcommop.joaquin.seceleaderboard.presentation.contestslist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.devcommop.joaquin.seceleaderboard.domain.use_cases.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContestsListViewModel @Inject constructor(
    private val appUseCases: AppUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

}