package com.devcommop.joaquin.seceleaderboard.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Result(
    val contest: Contest,
    val rows: List<Row>
    //@PrimaryKey var id: Int? = contest.id
)
//{
//    @get:PrimaryKey
//    val id
//        get() = contest.id.toInt()
//}