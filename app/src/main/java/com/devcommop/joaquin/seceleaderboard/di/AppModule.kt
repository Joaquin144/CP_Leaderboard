package com.devcommop.joaquin.seceleaderboard.di

import com.devcommop.joaquin.seceleaderboard.common.Constants
import com.devcommop.joaquin.seceleaderboard.data.remote.CFApi
import com.devcommop.joaquin.seceleaderboard.data.repository.CFRepositoryImpl
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import com.devcommop.joaquin.seceleaderboard.domain.use_cases.AppUseCases
import com.devcommop.joaquin.seceleaderboard.domain.use_cases.GetCfHandlesUseCase
import com.devcommop.joaquin.seceleaderboard.domain.use_cases.GetPartiesScoreUseCase
import com.devcommop.joaquin.seceleaderboard.domain.use_cases.GetScoreboardUseCase
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
    fun provideCFRepository(api: CFApi): CFRepository {
        return CFRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAppUseCases(repository: CFRepository): AppUseCases {
        return AppUseCases(
            getCfHanlesUseCase = GetCfHandlesUseCase(repository = repository),
            getPartiesScoreUseCase = GetPartiesScoreUseCase(repository = repository),
            getScoreboardUseCase = GetScoreboardUseCase(repository = repository)
        )
    }

}