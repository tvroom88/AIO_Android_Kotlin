package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.GithubRepository

class MvvmAdvancedMoreExampleViewModelFactory(
    private val githubRepository: GithubRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MvvmAdvancedMoreExampleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MvvmAdvancedMoreExampleViewModel(githubRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}