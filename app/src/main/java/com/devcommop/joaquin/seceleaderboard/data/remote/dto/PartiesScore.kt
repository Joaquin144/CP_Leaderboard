package com.devcommop.joaquin.seceleaderboard.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PartiesScore(
    val result: Result,
    val status: String,
    val comment: String? = null,
    @PrimaryKey val id: Int?
)