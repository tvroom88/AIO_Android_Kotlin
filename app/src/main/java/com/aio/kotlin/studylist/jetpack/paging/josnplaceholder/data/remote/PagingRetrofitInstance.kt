package com.aio.kotlin.studylist.jetpack.paging.josnplaceholder.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PagingRetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: PagingApi = retrofit.create(PagingApi::class.java)
}