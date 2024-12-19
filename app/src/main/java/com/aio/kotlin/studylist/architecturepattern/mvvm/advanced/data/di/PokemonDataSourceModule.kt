package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.di

import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.local.PokemonLocalDataSource
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.local.PokemonLocalDataSourceImpl
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.remote.PokemonRemoteDataSource
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.datasource.remote.PokemonRemoteDataSourceImpl
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.local.CoroutineTestLocalDataSource
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.local.CoroutineTestLocalDataSourceImpl
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
        pokemonLocalDataSourceImpl: PokemonLocalDataSourceImpl
    ): PokemonLocalDataSource


    @Binds
    @Singleton
    abstract fun provideRemoteDataSource(
        pokemonRemoteDataSourceImpl: PokemonRemoteDataSourceImpl
    ): PokemonRemoteDataSource
}
