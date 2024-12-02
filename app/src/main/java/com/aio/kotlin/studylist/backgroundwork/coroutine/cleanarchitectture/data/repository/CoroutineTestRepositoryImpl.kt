package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.local.CoroutineTestLocalDataSource
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.remote.CoroutineTestRemoteDataSource
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.mapper.toDomainModel
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.mapper.toEntity
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineComment
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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
    override suspend fun insertAllDataToLocal(list: List<CoroutineTest>): Result<List<CoroutineTest>> {
        val entityList = list.map { it.toEntity() }
        return coroutineTestLocalDataSource.insertAllData(entityList).map { it.map { f -> f.toDomainModel() } }
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


    // Local Comment
    override fun getAllCommentFromLocal() : Result<Flow<List<CoroutineComment>>>{
        return coroutineTestLocalDataSource.getAllCommentData().map { it.map { f -> f.map { t -> t.toDomainModel() }  } }
    }

    override suspend fun insertAllCommentToLocal(list: List<CoroutineComment>) : Result<List<CoroutineComment>>{
        val entityList = list.map { it.toEntity() }
        return coroutineTestLocalDataSource.insertAllCommentData(entityList).map { it.map { f -> f.toDomainModel() } }
    }


    // Remote(Retrofit 으로)에서 가져오는 부분
    override suspend fun getAllCoroutineTestResultDataFromRemote(): Result<List<CoroutineTest>> {
        return coroutineTestRemoteDataSource.fetchAllCoroutineTestData()
            .map { it.map { f -> f.toDomainModel() } }
    }

    override fun getAllCoroutineCommentResultDataFromRemote(): Flow<Result<List<CoroutineComment>>> =
        flow {
            emit(
                coroutineTestRemoteDataSource.fetchAllCoroutineCommentData()
                    .map { it.map { f -> f.toDomainModel() } })
        }
}