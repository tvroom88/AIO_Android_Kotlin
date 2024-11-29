package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.network

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.remote.CoroutineCommentDto
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.remote.CoroutineTestDto
import retrofit2.http.GET

/**
 * open api
 * https://jsonplaceholder.typicode.com/posts
 * https://jsonplaceholder.typicode.com/comments
 */
interface
CoroutineTestApi {
    @GET("posts")
    suspend fun getPostDataWithCoroutine(): List<CoroutineTestDto>

    @GET("comments")
    suspend fun getCommentsDataWithCoroutine(): List<CoroutineCommentDto>
}