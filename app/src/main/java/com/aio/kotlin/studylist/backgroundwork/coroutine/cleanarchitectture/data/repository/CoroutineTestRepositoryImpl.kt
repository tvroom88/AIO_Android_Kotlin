package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.remote.CoroutineTestRemoteDataSource
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.mapper.toDomainModel
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest

/**
 * 내가 봤을때는 Repository에서는 사실상 Mapper로 내가 원하는 데이터를 받아와주는 역할만한다.
 * 그것 외에는 Local, Remote에서 받아오는 정보를 모아주는 역할 정도만 하는 것 같다.
 */
class CoroutineTestRepositoryImpl(private val coroutineTestRemoteDataSource: CoroutineTestRemoteDataSource) :
    CoroutineTestRepository {
    override suspend fun getAllCoroutineTestResultData(): List<CoroutineTest> {
       return  coroutineTestRemoteDataSource.fetchAllCoroutineTestData()
            .map { it.toDomainModel() }
    }
}