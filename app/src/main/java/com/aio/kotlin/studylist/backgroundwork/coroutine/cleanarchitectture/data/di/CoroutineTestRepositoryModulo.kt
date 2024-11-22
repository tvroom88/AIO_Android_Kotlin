package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.di

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.remote.CoroutineTestRemoteDataSource
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository.CoroutineTestRepository
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository.CoroutineTestRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineTestRepositoryModulo {

    @Singleton
    @Provides
    fun provideCoroutineTestRepository(coroutineTestRemoteDataSource: CoroutineTestRemoteDataSource): CoroutineTestRepository {
        return CoroutineTestRepositoryImpl(coroutineTestRemoteDataSource)
    }
}