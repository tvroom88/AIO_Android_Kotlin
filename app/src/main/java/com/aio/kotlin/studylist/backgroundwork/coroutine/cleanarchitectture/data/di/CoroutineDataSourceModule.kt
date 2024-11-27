package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.di

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.local.CoroutineTestLocalDataSource
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.local.CoroutineTestLocalDataSourceImpl
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.remote.CoroutineTestRemoteDataSource
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.remote.CoroutineTestRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineDataSourceModule {

    @Binds
    @Singleton
    abstract fun provideLocalDataSource(
        coroutineTestLocalDataSourceImpl: CoroutineTestLocalDataSourceImpl
    ): CoroutineTestLocalDataSource

    @Binds
    @Singleton
    abstract fun provideRemoteDataSource(
        coroutineTestRemoteDataSourceImpl: CoroutineTestRemoteDataSourceImpl
    ): CoroutineTestRemoteDataSource
}