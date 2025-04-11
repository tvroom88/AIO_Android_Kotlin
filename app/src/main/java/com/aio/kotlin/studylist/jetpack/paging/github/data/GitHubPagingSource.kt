package com.aio.kotlin.studylist.jetpack.paging.github.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * PagingSource<Key, Value> 는 Paging 라이브러리가 데이터를 어떻게 로드할지를 정의하는 추상 클래스입니다.
 * Key : 페이지를 식별하는 값 (여기선 Int -> since 파라미터로 사용)
 * Value : 한 페이지에 들어갈 데이터 타입
 */
class GitHubPagingSource(
    private val api: GitHubApi
) : PagingSource<Int, GitHubUser>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubUser> { // Paging 라이브러리가 데이터 로드를 요청할 떄 호출
        Log.d("GitHubPagingSource", "params : $params")
        Log.d("GitHubPagingSource", "params.key : ${params.key}")

        return try {
            val since = params.key ?: 0 // 다음 페이지를 가져오기 위한 기준값. 처음 로드할 땐 null이므로 0으로 설정

            val users = api.getUsers(since) // Github API에 요청해서 유저 리스트를 가져옴.
            LoadResult.Page(
                data = users, // 가져온 사용자 목록
                prevKey = null, // 이전 페이지 키(보통 GitHub API에선 필요 없음
                nextKey = users.lastOrNull()?.id // 다음페이지. 마지막 유저의 id를 기준으로 설정.
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GitHubUser>): Int? = null
}