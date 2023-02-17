package com.devcommop.joaquin.seceleaderboard.domain.use_cases

import com.devcommop.joaquin.seceleaderboard.common.Resource
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Contest
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUpcomingContestsUseCase @Inject constructor(
    private val repository: CFRepository
) {
    operator fun invoke(): Flow<Resource<List<Contest>>> = flow{
        try{
            emit(Resource.Loading<List<Contest>>())
            val upcomingContestsList = repository.getUpcomingContestsList()
            emit(Resource.Success<List<Contest>>(upcomingContestsList))
        }catch (e: HttpException){//response of server isn't a success (for success-> resCode starts with 2)
            emit(Resource.Error<List<Contest>>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch(e : IOException){//when repo can't talk to server eg no internet connection or server is offline
            emit(Resource.Error<List<Contest>>(e.localizedMessage ?: "Couldn't reach server. Check your Internet connection"))
        }catch(e : Exception){
            emit(Resource.Error<List<Contest>>(e.message ?: "Codeforces Api response is FAILED due to unknown error"))
        }
    }
}