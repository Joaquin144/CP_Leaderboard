package com.devcommop.joaquin.seceleaderboard.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.PartiesScore

@Database(entities = [PartiesScore::class], version = 1)
@TypeConverters(Converters::class)
abstract class CachedContestsDb : RoomDatabase() {

    abstract val contestsDao: ContestsDao

    companion object {
        const val DATABASE_NAME = "cached_contests_db"
    }

}