package com.devcommop.joaquin.seceleaderboard.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore

@Dao
interface ContestsDao {

    @Query("SELECT * FROM partiesscore WHERE id=:id")
    suspend fun getContestById(id: Int): PartiesScore?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContest(score: PartiesScore)

}