package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.di

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.remote.CoroutineTestRemoteDataSource
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.remote.CoroutineTestRemoteDataSourceImpl
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.network.CoroutineTestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineDataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(coroutineTestApi: CoroutineTestApi): CoroutineTestRemoteDataSource =
        CoroutineTestRemoteDataSourceImpl(coroutineTestApi)
}