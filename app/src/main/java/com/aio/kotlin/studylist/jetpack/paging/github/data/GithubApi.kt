package com.aio.kotlin.studylist.jetpack.paging.github.data

import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("users")
    suspend fun getUsers(
        @Query("since") since:Int,
        @Query("per_page") perPage:Int = 3
    ): List<GitHubUser>
}