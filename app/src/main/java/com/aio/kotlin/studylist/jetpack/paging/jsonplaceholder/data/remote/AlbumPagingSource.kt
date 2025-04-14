package com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState

class AlbumPagingSource(
    private val api: PagingApi
) : PagingSource<Int, PagingAlbumItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PagingAlbumItem> {
        val page = params.key ?: 1
        val limit = params.loadSize

        return try {
            val response = api.getAlbums(limit = limit, page = page)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
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