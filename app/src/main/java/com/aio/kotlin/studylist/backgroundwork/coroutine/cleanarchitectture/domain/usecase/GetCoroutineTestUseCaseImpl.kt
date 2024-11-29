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
    override suspend operator fun invoke(): CoroutinesTestState<List<CoroutineTest>> {
        return try {
            CoroutinesTestState.success(coroutineTestRepository.getAllCoroutineTestResultDataFromRemote())
        } catch (e: MalformedURLException) {
            CoroutinesTestState.error("Invalid URL: ${e.localizedMessage}") // URL 형식 오류
        } catch (e: UnknownHostException) {
            CoroutinesTestState.error("Unknown host: ${e.localizedMessage}") // 도메인 없음
        } catch (e: HttpException) {
            // 서버 오류 처리 (HTTP 상태 코드 포함)
            val errorMessage = when (e.code()) {
                500 -> "Server error: Internal server error"
                404 -> "Server error: Resource not found"
                else -> "Server error: ${e.message()}"
            }
            CoroutinesTestState.error(errorMessage)
        } catch (e: Exception) {
            // 기타 오류 처리
            CoroutinesTestState.error("Unknown error: ${e.localizedMessage}")
        }
    }

    override fun getCoroutineCommentData(): Flow<Result<List<CoroutineComment>>> {
        return coroutineTestRepository.getAllCoroutineCommentResultDataFromRemote()
    }

    // Local
    override suspend fun getAllLocalData(): Result<List<CoroutineTest>> {
        return coroutineTestRepository.getAllFromLocal()
    }

    override suspend fun saveAllDataToLocal(list: List<CoroutineTest>): Result<Boolean> {
        return coroutineTestRepository.insertAllDataToLocal(list)
    }

    override suspend fun deleteAlLDataFromLocal(): Result<Boolean> {
        return coroutineTestRepository.removeAllData()
    }

    override suspend fun numOfDataInDb(): Result<Int> {
        return coroutineTestRepository.numOfDataInDb()
    }
}
