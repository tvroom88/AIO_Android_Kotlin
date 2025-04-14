package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local.DelayedPagingSource
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local.PagingItemDao
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local.PagingItemEntity
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.remote.AlbumPagingSource
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.remote.PagingAlbumItem
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.remote.PagingRetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PagingRepository(
    private val pagingItemDao: PagingItemDao,

    ) {

    // ------------------------- Local -------------------------
    fun getItemPager(): Flow<PagingData<PagingItemEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3, // 한 번에 읽을 데이터 수
                initialLoadSize = 3,
                prefetchDistance = 1, // ← 중요!
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                DelayedPagingSource(pagingItemDao.getDataByTimeStamp())
            }
        ).flow
            .map { pagingData ->
                pagingData
            }
    }

    suspend fun insertUser(user: PagingItemEntity) {
        pagingItemDao.insert(user)
    }

    suspend fun deleteAll() {
        pagingItemDao.deleteAll()
    }

    // ------------------------- Remote -------------------------
    fun getRemotePagingFlow(): Flow<PagingData<PagingAlbumItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3,
                initialLoadSize = 3,
                prefetchDistance = 1, // ← 중요!
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                AlbumPagingSource(PagingRetrofitInstance.api)
            }
        ).flow
    }
}
