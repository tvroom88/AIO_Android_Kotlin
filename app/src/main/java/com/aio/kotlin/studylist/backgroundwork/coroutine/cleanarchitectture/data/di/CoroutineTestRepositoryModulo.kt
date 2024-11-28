package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.di

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository.CoroutineTestRepository
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository.CoroutineTestRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineTestRepositoryModulo {

    @Binds
    abstract fun provideCoroutineTestRepository(
        coroutineTestRepositoryImpl: CoroutineTestRepositoryImpl
    ): CoroutineTestRepository
}