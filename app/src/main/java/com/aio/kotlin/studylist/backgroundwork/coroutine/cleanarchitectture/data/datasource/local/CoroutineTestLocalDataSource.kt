package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.local

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineTestEntity

interface CoroutineTestLocalDataSource {

    suspend fun insertData(coroutineTestEntity: CoroutineTestEntity): Result<CoroutineTestEntity>

    suspend fun insertAllData(list: List<CoroutineTestEntity>): Result<Boolean>

    suspend fun deleteData(coroutineTestEntity: CoroutineTestEntity): Result<CoroutineTestEntity>

    suspend fun deleteAllData(): Result<Boolean>

    suspend fun getAllData(): Result<List<CoroutineTestEntity>>

    suspend fun getDataCount(): Result<Int>
}