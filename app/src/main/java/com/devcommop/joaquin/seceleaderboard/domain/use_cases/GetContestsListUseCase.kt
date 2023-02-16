package com.devcommop.joaquin.seceleaderboard.domain.use_cases

import com.devcommop.joaquin.seceleaderboard.common.CustomException
import com.devcommop.joaquin.seceleaderboard.common.Resource
import com.devcommop.joaquin.seceleaderboard.domain.repository.CFRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetContestsListUseCase @Inject constructor(
    private val repository: CFRepository
) {
    operator fun invoke(docId: String): Flow<Resource<String>> = flow {
        try{
            emit(Resource.Loading<String>())
            val list = repository.getContests(docId = docId)
            if(list == "-1"){
                throw CustomException(message = "Some error occurred in contacting Firebase")
            }
            emit(Resource.Success<String>(list))
        }catch(e: Exception){
            emit(Resource.Error<String>(message = e.localizedMessage?:"some error occurred"))
        }
    }
}