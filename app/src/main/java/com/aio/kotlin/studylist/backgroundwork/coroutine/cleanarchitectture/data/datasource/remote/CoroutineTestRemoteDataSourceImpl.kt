package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.remote

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.remote.CoroutineTestDto
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.network.CoroutineTestApi


class CoroutineTestRemoteDataSourceImpl(private val coroutineTestApi: CoroutineTestApi) : CoroutineTestRemoteDataSource {
    override suspend fun fetchAllCoroutineTestData(): List<CoroutineTestDto> = coroutineTestApi.getRxRetrofitTestData()
}