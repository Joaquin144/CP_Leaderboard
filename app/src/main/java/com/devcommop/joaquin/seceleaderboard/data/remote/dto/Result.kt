package com.devcommop.joaquin.seceleaderboard.data.remote.dto

data class Result(
    val contest: Contest,
    val rows: List<Row>
)