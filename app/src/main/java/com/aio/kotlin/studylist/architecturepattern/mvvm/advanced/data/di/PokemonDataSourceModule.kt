package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.di

import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.remote.PokemonRemoteDataSource
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.remote.PokemonRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonDataSourceModule {
    @Binds
    @Singleton
    abstract fun provideLocalDataSource(
        pokemonRemoteDataSourceImpl: PokemonRemoteDataSourceImpl
    ): PokemonRemoteDataSource
}
