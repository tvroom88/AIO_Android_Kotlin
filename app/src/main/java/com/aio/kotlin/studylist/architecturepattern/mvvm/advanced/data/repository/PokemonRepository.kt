package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.repository

import androidx.annotation.WorkerThread
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    // Local
    suspend fun insertPokemonListToLocal(pokemonList:List<Pokemon>) : Result<Int>
    suspend fun fetchPokemonListFromLocal(page: Int): Result<List<Pokemon>>
    suspend fun fetchAllPokemonListFromLocal(page: Int): Result<List<Pokemon>>

    // Remote
    suspend fun fetchPokemonListFromRemote(page: Int): Result<List<Pokemon>>

    // Remote & Local
    @WorkerThread
    suspend fun fetchPokemonList(page: Int): Flow<Result<List<Pokemon>>>
}