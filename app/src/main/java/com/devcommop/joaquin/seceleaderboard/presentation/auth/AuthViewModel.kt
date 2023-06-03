package com.devcommop.joaquin.seceleaderboard.presentation.auth

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.devcommop.joaquin.seceleaderboard.domain.use_cases.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "##@@AuthVM"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val appUseCases: AppUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    init {
        Log.d(TAG, "I am initialised")
    }
}