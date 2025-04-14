package com.aio.kotlin.studylist.jetpack.paging.josnplaceholder

/**
 *
 * https://jinudmjournal.tistory.com/132
 *
 * 연결할 url들
 * https://jsonplaceholder.typicode.com/
 * https://jsonplaceholder.typicode.com/albums?_limit=10&_page=3
 *
 * 1. Paging 구현 순서
 * 2. Room DB 구현
 * 3. 서버에서 데이터 받아오기
 * 4. RemoteMediator
 * 5. Room의 Dao와 Repository 구현.
 * 6. Repository에서 Pager를 return할 함수 정의
 * 7. ViewModel과 LazyColumn 활용
 *
 * 2가지 방식이 있다.
 *
 * 1. Room + PagingSource (수동 구현, 네가 지금 하고 있는 방식)
 * 2. Room + Paging 3 자동 연동 방식 (추천 방식)
 *
 * 출처: https://jinudmjournal.tistory.com/132 [김누누:티스토리]
 */
class Info {
}