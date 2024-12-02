package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.local

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database.CoroutineCommentDao
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.database.CoroutineTestDao
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineCommentEntity
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineTestEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoroutineTestLocalDataSourceImpl @Inject constructor(
    private val coroutineTestDao: CoroutineTestDao,
    private val coroutineCommentDao: CoroutineCommentDao
) :
    CoroutineTestLocalDataSource {
    override suspend fun insertData(coroutineTestEntity: CoroutineTestEntity): Result<CoroutineTestEntity> =
        try {
            val result = coroutineTestDao.insertData(coroutineTestEntity)

            if (result >= 0L) {
                Result.success(coroutineTestEntity)
            } else {
                Result.failure(IllegalArgumentException())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun insertAllData(list: List<CoroutineTestEntity>): Result<List<CoroutineTestEntity>> =
        try {
            coroutineTestDao.insertAllData(list)
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun deleteData(coroutineTestEntity: CoroutineTestEntity): Result<CoroutineTestEntity> =
        try {
            val result = coroutineTestDao.deleteData(coroutineTestEntity)

            if (result > 0) {
                Result.success(coroutineTestEntity)
            } else {
                Result.failure(IllegalArgumentException())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }


    // 새로 추가한 deleteAll 구현
    override suspend fun deleteAllData(): Result<Boolean> =
        try {
            coroutineTestDao.deleteAll()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun getAllData(): Result<List<CoroutineTestEntity>> =
        try {
            val listOfData = coroutineTestDao.getAllData()
            Result.success(listOfData)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun getDataCount(): Result<Int> =
        try {
            val count = coroutineTestDao.getCount()
            Result.success(count)
        } catch (e: Exception) {
            Result.failure(e)
        }


    // Comment Data
    override fun getAllCommentData(): Result<Flow<List<CoroutineCommentEntity>>> =
        try {
            val result = coroutineCommentDao.getAllData()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }


    override suspend fun insertAllCommentData(list: List<CoroutineCommentEntity>): Result<List<CoroutineCommentEntity>> =
        try {
            coroutineCommentDao.insertAllData(list)
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun deleteAllCommentData(): Result<Boolean> {
        return Result.success(true)
    }
}
