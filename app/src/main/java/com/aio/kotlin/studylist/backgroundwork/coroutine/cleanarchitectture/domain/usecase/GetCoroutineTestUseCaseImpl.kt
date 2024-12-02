package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.usecase

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository.CoroutineTestRepository
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineComment
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate.CoroutinesTestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.MalformedURLException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

//ToDo : 문제 생겼다면 이곳 한번 체크해보기
@Singleton
class GetCoroutineTestUseCaseImpl @Inject constructor(
    private val coroutineTestRepository: CoroutineTestRepository
) : GetUseCase.GetCoroutineTestUseCase {

    // Remote
    override suspend operator fun invoke(): Result<List<CoroutineTest>> {
        return coroutineTestRepository.getAllCoroutineTestResultDataFromRemote()
    }

    override fun getCoroutineCommentData(): Flow<Result<List<CoroutineComment>>> {
        return coroutineTestRepository.getAllCoroutineCommentResultDataFromRemote()
    }

    // Local
    override suspend fun getAllLocalData(): Result<List<CoroutineTest>> {
        return coroutineTestRepository.getAllFromLocal()
    }

    override suspend fun saveAllDataToLocal(list: List<CoroutineTest>): Result<List<CoroutineTest>> {
        return coroutineTestRepository.insertAllDataToLocal(list)
    }

    override suspend fun deleteAlLDataFromLocal(): Result<Boolean> {
        return coroutineTestRepository.removeAllData()
    }

    override suspend fun numOfDataInDb(): Result<Int> {
        return coroutineTestRepository.numOfDataInDb()
    }


    override suspend fun saveAllCommentDataToLocal(list: List<CoroutineComment>): Result<List<CoroutineComment>> {
        return coroutineTestRepository.insertAllCommentToLocal(list)
    }

}
