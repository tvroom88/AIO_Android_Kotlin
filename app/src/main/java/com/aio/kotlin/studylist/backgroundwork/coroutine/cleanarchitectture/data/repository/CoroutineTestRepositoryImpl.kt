package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.local.CoroutineTestLocalDataSource
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.remote.CoroutineTestRemoteDataSource
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineTestEntity
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.mapper.toDomainModel
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.mapper.toEntity
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import javax.inject.Inject

/**
 * 내가 봤을때는 Repository에서는 사실상 Mapper로 내가 원하는 데이터를 받아와주는 역할만한다.
 * 그것 외에는 Local, Remote에서 받아오는 정보를 모아주는 역할 정도만 하는 것 같다.
 */
class CoroutineTestRepositoryImpl @Inject constructor(
    private val coroutineTestLocalDataSource: CoroutineTestLocalDataSource,
    private val coroutineTestRemoteDataSource: CoroutineTestRemoteDataSource
) : CoroutineTestRepository {

    // Local(Room DB)에서 가져오는 부분
    override suspend fun insertAllDataToLocal(list: List<CoroutineTest>): Result<Boolean> {
        val entityList = list.map { it.toEntity() }
        return coroutineTestLocalDataSource.insertAllData(entityList)
    }

    override suspend fun getAllFromLocal(): Result<List<CoroutineTest>> {
        return coroutineTestLocalDataSource.getAllData().map { it.map { f -> f.toDomainModel() } }
    }

    override suspend fun removeAllData(): Result<Boolean> {
        return coroutineTestLocalDataSource.deleteAllData()
    }

    override suspend fun numOfDataInDb(): Result<Int> {
        return coroutineTestLocalDataSource.getDataCount()
    }

    // Remote(Retrofit 으로)에서 가져오는 부분
    override suspend fun getAllCoroutineTestResultDataFromRemote(): List<CoroutineTest> {
        return coroutineTestRemoteDataSource.fetchAllCoroutineTestData()
            .map { it.toDomainModel() }
    }
}