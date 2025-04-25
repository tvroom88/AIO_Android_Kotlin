package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.common.NetworkResult
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.GithubRepository
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.remote.RemoteGithubResponse
import kotlinx.coroutines.launch

class MvvmAdvancedMoreExampleViewModel(private val githubRepository: GithubRepository) :
    ViewModel() {

    private val _response: MutableLiveData<NetworkResult<List<RemoteGithubResponse>>> =
        MutableLiveData()
    val response: LiveData<NetworkResult<List<RemoteGithubResponse>>> = _response


    fun fetchDogResponse(since:Int, perPage:Int) = viewModelScope.launch {
        githubRepository.getGithubResponse(since, perPage).collect { values ->
            _response.value = values
        }
    }
}