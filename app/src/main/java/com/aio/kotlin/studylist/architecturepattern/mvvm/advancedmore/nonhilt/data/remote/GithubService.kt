package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.remote

import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.Constants
import com.aio.kotlin.studylist.jetpack.paging.github.data.GitHubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET(Constants.USERS)
    suspend fun getGithubResponse(
        @Query("since") since:Int = 0,
        @Query("per_page") perPage:Int = 5
    ): Response<List<RemoteGithubResponse>>
}