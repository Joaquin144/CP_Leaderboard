package com.devcommop.joaquin.seceleaderboard.data.remote.dto

import java.text.SimpleDateFormat
import java.util.*

data class Contest(
    val id: Int,
    val name: String,
    val description: String,
    val durationSeconds: Int,
    val phase: String,
    val relativeTimeSeconds: Int,
    val startTimeSeconds: Int,
) {
    fun getStartTime(): String {
        val timestamp = startTimeSeconds.toLong()
        val timeD = Date(timestamp * 1000)
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss ")

        return sdf.format(timeD)
    }

    fun getDuration(): String {
        var minutes = durationSeconds / 60
        val hours = minutes / 60
        minutes -= hours * 60
        var time: String = ""
        if (hours < 9)
            time += "0"
        time += hours.toString()
        time += ':'
        if (minutes < 9)
            time += "0"
        time += minutes.toString()
        time += " hrs"
        return time
    }
}