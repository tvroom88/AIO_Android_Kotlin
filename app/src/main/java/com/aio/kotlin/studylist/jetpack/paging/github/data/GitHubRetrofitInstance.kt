package com.aio.kotlin.studylist.jetpack.paging.github.data

import com.aio.kotlin.studylist.jetpack.paging.github.ui.GitHubViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitHubRetrofitInstance {
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