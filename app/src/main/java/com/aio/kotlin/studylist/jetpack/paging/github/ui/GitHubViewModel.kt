package com.aio.kotlin.studylist.jetpack.paging.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aio.kotlin.studylist.jetpack.paging.github.data.GitHubRepository
import com.aio.kotlin.studylist.jetpack.paging.github.data.GitHubUser
import kotlinx.coroutines.flow.Flow

class GitHubViewModel(repository: GitHubRepository) : ViewModel() {
    val users: Flow<PagingData<GitHubUser>> = repository.getUsers()
        .flow
        .cachedIn(viewModelScope)
}