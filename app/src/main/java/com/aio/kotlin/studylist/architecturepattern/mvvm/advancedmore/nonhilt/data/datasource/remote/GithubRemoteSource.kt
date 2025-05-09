package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.datasource.remote

import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.entity.remote.RemoteGithubModel

/**
 * 1) Hilt 미사용 + Response 미사용 + Result 미사용 + Flow & SharedFlow
 * 2) Hilt 미사용 + Response 사용 + Result 미사용 + Flow & SharedFlow
 * 3) Hilt 미사용 + Response 미사용 + Result 사용 + Flow & SharedFlow
 * 4) Hilt 미사용 + Response 사용 + Result 사용 + Flow & SharedFlow
 */
interface GithubRemoteSource {

    // 1) Hilt 미사용 + Response 미사용 (X) + Result 미사용 (X) + Flow & SharedFlow
    suspend fun getGithubData(since: Int, perPage: Int): List<RemoteGithubModel>

    // 2) Hilt 미사용 + Response 사용 (O) + Result 미사용 (X) + Flow & SharedFlow
    suspend fun getGithubDataWithResponse(since: Int, perPage: Int): List<RemoteGithubModel>

    // 3) Hilt 미사용 + Response 미사용 (X) + Result 사용 (O) + Flow & SharedFlow
    suspend fun getGithubDataResult(since: Int, perPage: Int): Result<List<RemoteGithubModel>>

    // 4) Hilt 미사용 + Response 사용 (O) + Result 사용 (O) + Flow & SharedFlow
    suspend fun getGithubDataResultResponse(
        since: Int,
        perPage: Int
    ): Result<List<RemoteGithubModel>>


}