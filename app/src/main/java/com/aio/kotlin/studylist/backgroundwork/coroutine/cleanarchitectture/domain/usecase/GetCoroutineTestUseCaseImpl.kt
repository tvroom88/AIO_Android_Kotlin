package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.usecase

import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.repository.CoroutineTestRepository
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate.CoroutinesTestState
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

    override suspend operator fun invoke(): CoroutinesTestState<List<CoroutineTest>> {
        return try {
            CoroutinesTestState.success(coroutineTestRepository.getAllCoroutineTestResultData())
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
}