package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.network

import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.remote.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonListApi {
    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<PokemonResponse>


    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String): Response<PokemonResponse>
}