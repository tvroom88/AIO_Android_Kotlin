package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.repository

import androidx.annotation.WorkerThread
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.local.PokemonLocalDataSource
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.remote.PokemonRemoteDataSource
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.local.mapper.asDomain
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.local.mapper.asEntity
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {

    override suspend fun insertPokemonListToLocal(pokemonList: List<Pokemon>): Result<Int> =
        pokemonLocalDataSource.insertPokemonList(pokemonList.asEntity())

    override suspend fun fetchPokemonListFromLocal(page: Int): Result<List<Pokemon>> =
        pokemonLocalDataSource.getPokemonListFromLocalDb(page).map { v -> v.asDomain() }

    override suspend fun fetchAllPokemonListFromLocal(page: Int): Result<List<Pokemon>> =
        pokemonLocalDataSource.getAllPokemonListFromLocalDb(page).map { v -> v.asDomain() }

    override suspend fun fetchPokemonListFromRemote(page: Int): Result<List<Pokemon>> =
        pokemonRemoteDataSource.fetchPokemonList(page)

    @WorkerThread
    override suspend fun fetchPokemonList(page: Int): Flow<Result<List<Pokemon>>> = flow {
        val pokemonList = fetchPokemonListFromLocal(page)
        if (pokemonList.isFailure) {
            emit(pokemonList)
            return@flow
        }

        if (pokemonList.getOrNull()?.isNotEmpty()!!) {
            emit(fetchAllPokemonListFromLocal(page))
        } else {
            fetchPokemonListFromRemote(page).onSuccess { // 네트워크 통신으로 데이터를 잘 받아 온다면
                it.forEach { pokemon -> pokemon.page = page }
                insertPokemonListToLocal(it).onSuccess {
                    val insertedData = fetchAllPokemonListFromLocal(page)
                    emit(insertedData)
                }
            }
        }
    }
}