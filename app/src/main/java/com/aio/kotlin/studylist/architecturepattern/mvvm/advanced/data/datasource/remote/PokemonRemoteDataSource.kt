package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.remote

import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.remote.Pokemon

interface PokemonRemoteDataSource {
    suspend fun fetchPokemonList(page:Int): Result<List<Pokemon>>
}