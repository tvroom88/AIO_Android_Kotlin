package com.aio.kotlin.studylist.jetpack.paging.josnplaceholder.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

//https://jsonplaceholder.typicode.com/albums?_limit=10&_page=3
interface PagingApi {
    @GET("albums")
    suspend fun getAlbums(
        @Query("_limit") limit: Int,
        @Query("_page") page: Int
    ): List<PagingAlbumItem>
}