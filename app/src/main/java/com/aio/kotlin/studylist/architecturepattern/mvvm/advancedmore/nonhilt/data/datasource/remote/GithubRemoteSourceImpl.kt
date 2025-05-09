package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.datasource.remote

import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.entity.remote.RemoteGithubModel

/**
 * 1) Hilt 미사용 + Response 미사용 + Result 미사용 + Flow & SharedFlow
 * 2) Hilt 미사용 + Response 사용 + Result 미사용 + Flow & SharedFlow
 * 3) Hilt 미사용 + Response 미사용 + Result 사용 + Flow & SharedFlow
 * 4) Hilt 미사용 + Response 사용 + Result 사용 + Flow & SharedFlow
 */
class RemoteDataSource(private val githubServiceApi: GithubServiceApi) : GithubRemoteSource {

    // 1) Hilt 미사용 + Response 미사용 + Result 미사용 + Flow & SharedFlow
    override suspend fun getGithubData(
        since: Int,
        perPage: Int
    ): List<RemoteGithubModel> = githubServiceApi.getGithubData(since, perPage)

    // 2) Hilt 미사용 + Response 사용 + Result 미사용 + Flow & SharedFlow
    override suspend fun getGithubDataWithResponse(
        since: Int,
        perPage: Int
    ): List<RemoteGithubModel> {
        val response = githubServiceApi.getGithubDataWithResponse(since, perPage)

        return if (response.isSuccessful) {
            response.body()?.let { // 성공
                it
            } ?: arrayListOf() // 실패 1 : 응답의 body가 비어있을 경우
        } else {
            arrayListOf() // 실패 2 : 네트워크 코드가 200 ~ 299 가 아닐 경우
        }
    }

    // 3) Hilt 미사용 + Response 미사용 + Result 사용 + Flow & SharedFlow
    override suspend fun getGithubDataResult(
        since: Int,
        perPage: Int
    ): Result<List<RemoteGithubModel>> {
        val response =
            githubServiceApi.getGithubDataWithResponse(since, perPage)
        return response.body()?.let { // 성공
            Result.success(it)
        } ?: Result.failure(NullPointerException("Body is null")) // 실패 1 : 응답의 body가 비어있을 경우
    }

    // 4) Hilt 미사용 + Response 사용 + Result 사용 + Flow & SharedFlow
    override suspend fun getGithubDataResultResponse(
        since: Int,
        perPage: Int
    ): Result<List<RemoteGithubModel>> = try {
        val response =
            githubServiceApi.getGithubDataWithResponse(since, perPage)
        if (response.isSuccessful) {
            response.body()?.let { // 응답이 성공적이면 Response.body()를 Result.Success로 변환하여 반환
                Result.success(it)
            } ?: Result.failure(NullPointerException("Body is null"))
        } else {
            Result.failure(Exception("Error: ${response.code()} - ${response.message()}")) // 200..299가 아닐 경우
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}
