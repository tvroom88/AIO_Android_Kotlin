package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.local

import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.local.PokemonEntity

interface PokemonLocalDataSource {
    suspend fun insertPokemonList(list: List<PokemonEntity>?) : Result<Int>
    suspend fun getPokemonListFromLocalDb(page:Int): Result<List<PokemonEntity>>
    suspend fun getAllPokemonListFromLocalDb(page:Int): Result<List<PokemonEntity>>
    suspend fun getAllData(): Result<List<PokemonEntity>>
}