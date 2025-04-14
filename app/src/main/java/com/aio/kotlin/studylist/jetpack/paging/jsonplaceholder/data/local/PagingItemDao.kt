package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PagingItemDao {

    @Insert
    suspend fun insert(items: PagingItemEntity)

    @Insert
    suspend fun insertAll(items: List<PagingItemEntity>)

    // key - value 형식
    // key : paging 할 기준 값
    // value : 결과로 받을 타입
    @Query("SELECT * FROM paging")
    fun getData(): PagingSource<Int, PagingItemEntity>


    // 컬럼의 데이터를 가져오는 dao 작성
    // 리턴 타입이 반드시 pagingSource 타입이어야 함
    @Query("SELECT * FROM paging ORDER BY 'timestamp' DESC")
    fun getDataByTimeStamp(): PagingSource<Int, PagingItemEntity>

    @Query("SELECT * FROM paging ORDER BY 'timestamp' DESC LIMIT :limit OFFSET :offset")
    suspend fun getByLimitOffset(limit: Int, offset: Int): List<PagingItemEntity>


    @Query("DELETE FROM paging")
    suspend fun deleteAll()
}