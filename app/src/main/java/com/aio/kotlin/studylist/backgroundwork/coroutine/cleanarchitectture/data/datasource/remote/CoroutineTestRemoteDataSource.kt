package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.remote

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.remote.CoroutineTestDto

interface CoroutineTestRemoteDataSource {
    suspend fun fetchAllCoroutineTestData(): List<CoroutineTestDto>
}