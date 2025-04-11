package com.aio.kotlin.studylist.jetpack.paging.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.studylist.jetpack.paging.github.data.GitHubRepository

class GitHubViewModelFactory(
    private val repository: GitHubRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitHubViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GitHubViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}