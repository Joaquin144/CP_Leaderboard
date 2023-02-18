package com.devcommop.joaquin.seceleaderboard.presentation.common

/**
 * This class is only used for sending events to those screens who are interested in listening them; events such as ShowError, ShowError etc. so that they can act accordingly to display a AlertDialog or ShimmerEffect.
 */
sealed class UiEvent {
    data class ShowError(val message: String): UiEvent()
    object ShowLoading: UiEvent()
}