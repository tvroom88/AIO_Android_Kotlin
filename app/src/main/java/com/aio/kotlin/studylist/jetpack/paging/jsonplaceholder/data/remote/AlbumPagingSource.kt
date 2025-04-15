package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class AlbumPagingSource(
    private val api: PagingApi,
    private var startId: Int
) : PagingSource<Int, PagingAlbumItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PagingAlbumItem> {
        Log.d("AlbumPagingSource", "startId : $startId")
        val page = params.key ?: ((startId / params.loadSize) + 1)
        val limit = params.loadSize

        return try {
            val response = api.getAlbums(limit = limit, page = page)
            Log.d("AlbumPagingSource", "Loaded items count: ${response.size}")
            Log.d("AlbumPagingSource", "Loaded item IDs: ${response.map { it }}")

//            LoadResult.Page(
//                data = response,
//                prevKey = if (offset == 0) null else offset - limit,
//                nextKey = if (response.isEmpty()) null else offset + response.size
//            )

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PagingAlbumItem>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }

}