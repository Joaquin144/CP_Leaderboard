package com.devcommop.joaquin.seceleaderboard.domain.use_cases

import com.devcommop.joaquin.seceleaderboard.common.Constants
import com.devcommop.joaquin.seceleaderboard.common.CustomException
import com.devcommop.joaquin.seceleaderboard.common.Resource
import com.devcommop.joaquin.seceleaderboard.data.remote.dto.Row
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPartiesScoreUseCase @Inject constructor(
    private val repository: CFRepository
) {
    operator fun invoke(options: Map<String,String>): Flow<Resource<List<Row>>> = flow{
        try{
            emit(Resource.Loading<List<Row>>())
            val partiesScore = repository.getPartiesScore(options = options)
            if(partiesScore.status == Constants.CF_API_FAILED_STATUS) {
                throw CustomException(message = partiesScore.comment.toString())
            }
            val rowsList = partiesScore.result.rows
            emit(Resource.Success<List<Row>>(rowsList))
        }catch (e: HttpException){//response of server isn't a success (for success-> resCode starts with 2)
            emit(Resource.Error<List<Row>>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch(e : IOException){//when repo can't talk to server eg no internet connection or server is offline
            emit(Resource.Error<List<Row>>(e.localizedMessage ?: "Couldn't reach server. Check your Internet connection"))
        }catch(e : Exception){
            emit(Resource.Error<List<Row>>(e.message ?: "Codeforces Api response is FAILED"))
        }
    }
}