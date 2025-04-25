package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.remote

import retrofit2.Response

class RemoteDataSource(private val githubService: GithubService) {
    suspend fun getGithubResponse(since:Int, perPage:Int): Response<List<RemoteGithubResponse>> =
        githubService.getGithubResponse(since, perPage)
}