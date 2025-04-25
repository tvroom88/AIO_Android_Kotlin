package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data

import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.common.BaseApiResponse
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.common.NetworkResult
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.remote.RemoteDataSource
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.remote.RemoteGithubResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GithubRepository(private val githubRemoteDataSource: RemoteDataSource) : BaseApiResponse() {

    suspend fun getGithubResponse(since:Int, perPage:Int): Flow<NetworkResult<List<RemoteGithubResponse>>> {
        return flow {
            emit(safeApiCall { githubRemoteDataSource.getGithubResponse(since, perPage) })
        }.flowOn(Dispatchers.IO)
    }
}
