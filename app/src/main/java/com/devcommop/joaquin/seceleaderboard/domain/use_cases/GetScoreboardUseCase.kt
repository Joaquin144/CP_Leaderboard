package com.devcommop.joaquin.seceleaderboard.domain.use_cases

import com.devcommop.joaquin.seceleaderboard.common.Resource
import com.devcommop.joaquin.seceleaderboard.data.remote.custom.ScoreboardResult
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetScoreboardUseCase @Inject constructor(
    private val repository: CFRepository
) {
    operator fun invoke(docId: String): Flow<Resource<ScoreboardResult>> = flow{
        try{
            emit(Resource.Loading<ScoreboardResult>())
            val scoreboard = repository.getScoreboard(docId = docId)
            emit(Resource.Success<ScoreboardResult>(scoreboard))
        }catch (e: HttpException){//response of server isn't a success (for success-> resCode starts with 2)
            emit(Resource.Error<ScoreboardResult>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch(e : IOException){//when repo can't talk to server eg no internet connection or server is offline
            emit(Resource.Error<ScoreboardResult>(e.localizedMessage ?: "Couldn't reach server. Check your Internet connection"))
        }catch(e : Exception){
            emit(Resource.Error<ScoreboardResult>(e.message ?: "Codeforces Api response is FAILED due to unknown error"))
        }
    }
}