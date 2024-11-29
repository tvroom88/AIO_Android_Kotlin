package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.mapper

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.local.CoroutineTestEntity
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.entity.remote.CoroutineTestDto
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest

/**
 * 이런 개념을 확장함수라고 하는 것 같다.
 */
fun CoroutineTestDto.toDomainModel(): CoroutineTest {
    return CoroutineTest(
        userId = this.userId,
        id = this.id,
        title = this.title,
        body = this.body
    )
}

fun CoroutineTestEntity.toDomainModel(): CoroutineTest {
    return CoroutineTest(
        userId = this.userId,
        id = this.customId,
        title = this.title,
        body = this.body
    )
}

fun CoroutineTest.toEntity(): CoroutineTestEntity {
    return CoroutineTestEntity(
        myId = null,
        userId = this.userId,
        customId = this.id,
        title = this.title,
        body = this.body
    )
}