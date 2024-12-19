package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.local

import android.util.Log
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.database.PokemonDao
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.local.PokemonEntity
import javax.inject.Inject

class PokemonLocalDataSourceImpl @Inject constructor(private val pokemonDao: PokemonDao) :
    PokemonLocalDataSource {

    override suspend fun insertPokemonList(list: List<PokemonEntity>?): Result<Int> = try {
        if (list.isNullOrEmpty()) {
            Result.failure(Exception("Empty Pokemon list fetched from API"))
        } else {
            pokemonDao.insertPokemonList(list)
            Result.success(0)
        }

    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getPokemonListFromLocalDb(page: Int): Result<List<PokemonEntity>> = try {
        val result = pokemonDao.getPokemonList(page)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getAllPokemonListFromLocalDb(page: Int): Result<List<PokemonEntity>> =
        try {
            Log.d("pagepage", "page : $page")
            val result = pokemonDao.getAllPokemonList(page)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun getAllData() : Result<List<PokemonEntity>> {
        try {
            val result = pokemonDao.getAllData()
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

}