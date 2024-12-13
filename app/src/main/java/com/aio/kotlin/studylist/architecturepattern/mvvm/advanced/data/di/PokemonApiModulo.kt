package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.di

import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.network.PokemonListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonApiModulo {
    @Singleton
    @Provides
    fun providePokemonApi(@PokemonNetworkModulo.PokemonRetrofit retrofit: Retrofit): PokemonListApi {
        return retrofit.create(PokemonListApi::class.java)
    }
}