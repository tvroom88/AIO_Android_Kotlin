package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.datasource.remote

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.remote.CoroutineCommentDto
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.remote.CoroutineTestDto
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.network.CoroutineTestApi
import javax.inject.Inject


class CoroutineTestRemoteDataSourceImpl @Inject constructor(private val coroutineTestApi: CoroutineTestApi) :
    CoroutineTestRemoteDataSource {

    // Flow 미사용
    override suspend fun fetchAllCoroutineTestData(): Result<List<CoroutineTestDto>> = try{
        val result = coroutineTestApi.getPostDataWithCoroutine()
        Result.success(result)
    }catch (e: Exception) {
        Result.failure(e)
    }

    // Flow 사용
    override suspend fun fetchAllCoroutineCommentData(): Result<List<CoroutineCommentDto>> = try {
        val result = coroutineTestApi.getCommentsDataWithCoroutine()
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }
}