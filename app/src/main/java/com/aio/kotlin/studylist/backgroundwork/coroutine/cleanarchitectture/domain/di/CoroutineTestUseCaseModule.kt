package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.di

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.usecase.GetCoroutineTestUseCaseImpl
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.usecase.GetUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineTestUseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGetCoroutineTestUseCase(
        getCoroutineTestUseCaseImpl: GetCoroutineTestUseCaseImpl
    ): GetUseCase.GetCoroutineTestUseCase

}