package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.mapper

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineCommentEntity
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineTestEntity
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.remote.CoroutineCommentDto
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineComment
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest

fun CoroutineCommentDto.toDomainModel(): CoroutineComment {
    return CoroutineComment(
        postId = this.postId,
        id = this.id,
        name = this.name,
        email = this.email,
        body = this.body
    )
}


fun CoroutineCommentEntity.toDomainModel(): CoroutineComment {
    return CoroutineComment(
        postId = this.postId,
        id = this.customId,
        name = this.name,
        email = this.email,
        body = this.body
    )
}

fun CoroutineComment.toEntity(): CoroutineCommentEntity {
    return CoroutineCommentEntity(
        myId = null,
        postId = this.postId,
        customId = this.id,
        name = this.name,
        email = this.email,
        body = this.body
    )
}