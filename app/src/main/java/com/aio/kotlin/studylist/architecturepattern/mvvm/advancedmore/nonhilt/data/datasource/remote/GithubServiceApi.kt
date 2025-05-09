package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.datasource.remote

import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.Constants
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.entity.remote.RemoteGithubModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Response<List<RemoteGithubModel>> :
 *
 * 결과 값에 Response를 넣으면 Response 코드 별로 세세한 분기를 좀 더 쉽게 처리 할 수 있다.
 * 서버 응답을 촘촘하게 할 필요가 없다면 굳이 Response로 받을 필요는 없다.
 *
 */
interface GithubServiceApi {

    // Return 값이 기본 값이다.
    @GET(Constants.USERS)
    suspend fun getGithubData(
        @Query("since") since: Int = 0,
        @Query("per_page") perPage: Int = 5
    ): List<RemoteGithubModel>

    // Return 값에 Response 안에 넣어서 넘긴다.
    @GET(Constants.USERS)
    suspend fun getGithubDataWithResponse(
        @Query("since") since: Int = 0,
        @Query("per_page") perPage: Int = 5
    ): Response<List<RemoteGithubModel>>
}