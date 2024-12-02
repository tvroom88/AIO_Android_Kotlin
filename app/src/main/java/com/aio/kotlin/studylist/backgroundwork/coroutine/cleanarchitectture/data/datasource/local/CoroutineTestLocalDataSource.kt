package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.local

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineCommentEntity
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineTestEntity
import kotlinx.coroutines.flow.Flow

interface CoroutineTestLocalDataSource {

    suspend fun insertData(coroutineTestEntity: CoroutineTestEntity): Result<CoroutineTestEntity>

    suspend fun insertAllData(list: List<CoroutineTestEntity>): Result<List<CoroutineTestEntity>>

    suspend fun deleteData(coroutineTestEntity: CoroutineTestEntity): Result<CoroutineTestEntity>

    suspend fun deleteAllData(): Result<Boolean>

    suspend fun getAllData(): Result<List<CoroutineTestEntity>>

    suspend fun getDataCount(): Result<Int>


    // Comment 데이터
    fun getAllCommentData(): Result<Flow<List<CoroutineCommentEntity>>>

    suspend fun insertAllCommentData(list: List<CoroutineCommentEntity>): Result<List<CoroutineCommentEntity>>

    suspend fun deleteAllCommentData(): Result<Boolean>

}