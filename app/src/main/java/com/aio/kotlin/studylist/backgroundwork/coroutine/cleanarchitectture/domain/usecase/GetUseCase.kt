package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.usecase

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate.CoroutinesTestState

interface GetUseCase {
    interface GetCoroutineTestUseCase {
        suspend operator fun invoke(): CoroutinesTestState<List<CoroutineTest>>
        suspend fun getAllLocalData(): Result<List<CoroutineTest>>
        suspend fun saveAllDataToLocal(list: List<CoroutineTest>): Result<Boolean>
        suspend fun deleteAlLDataFromLocal(): Result<Boolean>
        suspend fun numOfDataInDb(): Result<Int>
    }
}