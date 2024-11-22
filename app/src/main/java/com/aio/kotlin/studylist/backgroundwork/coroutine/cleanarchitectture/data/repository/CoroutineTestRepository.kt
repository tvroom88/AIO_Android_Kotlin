package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest

interface CoroutineTestRepository {
    suspend fun getAllCoroutineTestResultData(): List<CoroutineTest>
}