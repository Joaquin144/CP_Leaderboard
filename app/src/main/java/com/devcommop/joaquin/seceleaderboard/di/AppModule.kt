package com.devcommop.joaquin.seceleaderboard.di

import android.app.Application
import androidx.room.Room
import com.devcommop.joaquin.seceleaderboard.common.Constants
import com.devcommop.joaquin.seceleaderboard.data.cache.CachedContestsDb
import com.devcommop.joaquin.seceleaderboard.data.remote.CFApi
import com.devcommop.joaquin.seceleaderboard.data.repository.CFRepositoryImpl
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import com.devcommop.joaquin.seceleaderboard.domain.use_cases.*
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuth() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideCFApi(): CFApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CFApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCachedContestsDb(app: Application): CachedContestsDb{
        return Room.databaseBuilder(
            app.applicationContext,
            CachedContestsDb::class.java,
            CachedContestsDb.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCFRepository(api: CFApi, cachedContestsDb: CachedContestsDb): CFRepository {
        return CFRepositoryImpl(api, cachedContestsDb.contestsDao)
    }

    @Provides
    @Singleton
    fun provideAppUseCases(repository: CFRepository): AppUseCases {
        return AppUseCases(
            getCfHanlesUseCase = GetCfHandlesUseCase(repository = repository),
            getPartiesScoreUseCase = GetPartiesScoreUseCase(repository = repository),
            getScoreboardUseCase = GetScoreboardUseCase(repository = repository),
            getContestsListUseCase = GetContestsListUseCase(repository = repository),
            getUpcomingContestsListUseCase = GetUpcomingContestsUseCase(repository = repository)
        )
    }

}