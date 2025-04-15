package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

class DelayedPagingSource(private val original: PagingSource<Int, PagingItemEntity>) :
    PagingSource<Int, PagingItemEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PagingItemEntity> {
        Log.d("DelayedPagingSource", "key : ${params.key}")

        val result = original.load(params)

        when (result) {
            is LoadResult.Page -> {
                Log.d("DelayedPagingSource", "Loaded items: ${result.data}")
                Log.d("DelayedPagingSource", "PrevKey: ${result.prevKey}, NextKey: ${result.nextKey}")
            }
            is LoadResult.Error -> {
                Log.e("DelayedPagingSource", "Load error: ${result.throwable}")
            }

            is LoadResult.Invalid -> Log.e("DelayedPagingSource", "Load error")
        }

        delay(1000L) // ← 페이지당 3초 지연
        return result
    }

    override fun getRefreshKey(state: PagingState<Int, PagingItemEntity>): Int? {
        Log.d(
            "DelayedPagingSource",
            "state : $state,  anchorPosition ${state.anchorPosition}, pages  ${state.pages}"
        )
        return original.getRefreshKey(state)
    }
}