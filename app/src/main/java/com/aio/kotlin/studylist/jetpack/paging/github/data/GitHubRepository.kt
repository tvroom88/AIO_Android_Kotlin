package com.aio.kotlin.studylist.jetpack.paging.github.data

import androidx.paging.Pager
import androidx.paging.PagingConfig

class GitHubRepository(private val api: GitHubApi) {
    fun getUsers(): Pager<Int, GitHubUser> {
        return Pager(
            config = PagingConfig(pageSize = 3),
            pagingSourceFactory = { GitHubPagingSource(api) }
        )
    }
}