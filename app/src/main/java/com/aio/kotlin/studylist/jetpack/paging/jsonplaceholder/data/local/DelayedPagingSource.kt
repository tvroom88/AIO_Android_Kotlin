package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

class DelayedPagingSource(private val original: PagingSource<Int, PagingItemEntity>) :
    PagingSource<Int, PagingItemEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PagingItemEntity> {
        Log.d("DelayedPagingSource", "key : ${params.key}")
        delay(1000L) // ← 페이지당 3초 지연
        return original.load(params)
    }

    override fun getRefreshKey(state: PagingState<Int, PagingItemEntity>): Int? {
        Log.d(
            "DelayedPagingSource",
            "state : $state,  anchorPosition ${state.anchorPosition}, pages  ${state.pages}"
        )
        return original.getRefreshKey(state)
    }
}