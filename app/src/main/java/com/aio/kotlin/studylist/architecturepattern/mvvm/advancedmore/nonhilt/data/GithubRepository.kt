package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data

import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.datasource.remote.RemoteDataSource
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.entity.remote.RemoteGithubModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubRepository(private val githubRemoteDataSource: RemoteDataSource) {

    suspend fun getGithubResponse(since: Int, perPage: Int): Flow<Result<List<RemoteGithubModel>>> =
        flow {
            emit(githubRemoteDataSource.getGithubDataResultResponse(since, perPage))
        }
}
