package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.remote

data class CoroutineCommentDto(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)