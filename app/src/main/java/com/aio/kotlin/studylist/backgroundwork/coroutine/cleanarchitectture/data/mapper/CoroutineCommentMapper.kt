package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.mapper

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.remote.CoroutineCommentDto
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineComment

fun CoroutineCommentDto.toDomainModel(): CoroutineComment {
    return CoroutineComment(
        postId = this.postId,
        id = this.id,
        name = this.name,
        email = this.email,
        body = this.body
    )
}
