package com.devcommop.joaquin.seceleaderboard.data.remote.dto

data class Row(
    val party: Party,
    val penalty: Int,
    val points: Int,
    val rank: Int
)