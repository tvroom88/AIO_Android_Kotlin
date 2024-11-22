package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.di

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.network.CoroutineTestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineTestApiModule {
    @Singleton
    @Provides
    fun provideMarvelApi(@CoroutineTestNetworkModulo.CoroutineTestRetrofit retrofit: Retrofit): CoroutineTestApi {
        return retrofit.create(CoroutineTestApi::class.java)
    }
}