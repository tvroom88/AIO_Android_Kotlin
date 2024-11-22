package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.network

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.remote.CoroutineTestDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CoroutineTestApi {
    @GET("posts")  // 데이터를 한번만 받아오기 때문에 Single을 사용한다.
    suspend fun getRxRetrofitTestData(): List<CoroutineTestDto>
}