package com.aio.kotlin.studylist.jetpack.paging.josnplaceholder.data

import com.aio.kotlin.studylist.jetpack.paging.github.data.GitHubApi
import com.aio.kotlin.studylist.jetpack.paging.github.data.GitHubRepository
import com.aio.kotlin.studylist.jetpack.paging.github.ui.GitHubViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PagingRetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: GitHubApi = retrofit.create(GitHubApi::class.java)
    private val repository = GitHubRepository(api)

    fun provideViewModelFactory(): GitHubViewModelFactory {
        return GitHubViewModelFactory(repository)
    }
}