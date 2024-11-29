package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineComment
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import kotlinx.coroutines.flow.Flow

interface CoroutineTestRepository {

    // Local
    suspend fun insertAllDataToLocal(list: List<CoroutineTest>): Result<Boolean>

    suspend fun getAllFromLocal(): Result<List<CoroutineTest>>

    suspend fun removeAllData(): Result<Boolean>

    suspend fun numOfDataInDb(): Result<Int>

    // Remote
    suspend fun getAllCoroutineTestResultDataFromRemote(): List<CoroutineTest>

    fun getAllCoroutineCommentResultDataFromRemote(): Flow<Result<List<CoroutineComment>>>
}
