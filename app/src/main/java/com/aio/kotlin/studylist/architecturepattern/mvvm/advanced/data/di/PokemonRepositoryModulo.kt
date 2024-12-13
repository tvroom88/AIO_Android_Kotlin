package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.di

import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.repository.PokemonRepository
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonRepositoryModulo {

    @Binds
    abstract fun providePokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository

}