package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.usecase

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository.CoroutineTestRepository
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate.CoroutinesTestState
import javax.inject.Inject
import javax.inject.Singleton

//ToDo : 문제 생겼다면 이곳 한번 체크해보기
@Singleton
class GetCoroutineTestUseCaseImpl @Inject constructor(
    private val coroutineTestRepository: CoroutineTestRepository
) : GetUseCase.GetCoroutineTestUseCase {

    override suspend operator fun invoke(): CoroutinesTestState<List<CoroutineTest>> {
        return try {
            CoroutinesTestState.success(coroutineTestRepository.getAllCoroutineTestResultData())
        } catch (e: Exception) {
            CoroutinesTestState.error(e.message)
        }
    }
}