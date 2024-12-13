package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.repository

import androidx.annotation.WorkerThread
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.remote.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    @WorkerThread
    fun fetchPokemonList(page: Int): Flow<Result<List<Pokemon>>>
}