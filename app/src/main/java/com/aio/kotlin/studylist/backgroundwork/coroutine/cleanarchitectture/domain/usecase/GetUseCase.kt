package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.usecase

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineComment
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate.CoroutinesTestState
import kotlinx.coroutines.flow.Flow

interface GetUseCase {
    interface GetCoroutineTestUseCase {
        
        // Remote function들
        suspend operator fun invoke(): CoroutinesTestState<List<CoroutineTest>>
        fun getCoroutineCommentData(): Flow<Result<List<CoroutineComment>>>

        // Local function들
        suspend fun getAllLocalData(): Result<List<CoroutineTest>>
        suspend fun saveAllDataToLocal(list: List<CoroutineTest>): Result<Boolean>
        suspend fun deleteAlLDataFromLocal(): Result<Boolean>
        suspend fun numOfDataInDb(): Result<Int>
    }
}