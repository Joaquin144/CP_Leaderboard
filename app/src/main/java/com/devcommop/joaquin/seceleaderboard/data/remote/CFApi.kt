package com.devcommop.joaquin.seceleaderboard.data.remote

import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CFApi {

    @GET("/api/contest.standings")
    suspend fun getPartiesScore(@QueryMap options: Map<String,String>): PartiesScore
    //suspend fun getPartiesScore(@Query("contestId") contestId: String): PartiesScore

}