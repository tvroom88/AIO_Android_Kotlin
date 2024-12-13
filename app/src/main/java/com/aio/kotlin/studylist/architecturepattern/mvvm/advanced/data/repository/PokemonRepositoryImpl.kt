package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.repository

import androidx.annotation.WorkerThread
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.remote.PokemonRemoteDataSource
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.remote.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    @WorkerThread
    override fun fetchPokemonList(page: Int): Flow<Result<List<Pokemon>>> = flow {
        emit(pokemonRemoteDataSource.fetchPokemonList(page))
    }
}