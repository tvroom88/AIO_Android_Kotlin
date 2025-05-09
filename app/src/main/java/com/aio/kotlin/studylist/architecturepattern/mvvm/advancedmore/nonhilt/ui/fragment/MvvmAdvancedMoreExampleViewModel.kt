package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.GithubRepository
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.entity.remote.RemoteGithubModel
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui.UiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MvvmAdvancedMoreExampleViewModel(private val githubRepository: GithubRepository) :
    ViewModel() {

    private val _response: MutableLiveData<UiState<List<RemoteGithubModel>>> =
        MutableLiveData(UiState.INIT)
    val response: LiveData<UiState<List<RemoteGithubModel>>> = _response

    fun fetchGithubData(since: Int, perPage: Int) = viewModelScope.launch {
        githubRepository.getGithubResponse(since, perPage)
            .onStart {
                _response.value = UiState.Loading
            }.catch {
                Log.d("GithubGithub", "fetchGithubData : catch")
                _response.value = UiState.Error(it.message ?: "Error")
            }.collectLatest { result ->
                result.onSuccess { data ->
                    Log.d("GithubGithub", "fetchGithubData : collectLatest : success")
                    _response.value = UiState.Success(data)
                }.onFailure {
                    Log.d("GithubGithub", "fetchGithubData : collectLatest : failure")
                    _response.value = UiState.Error(it.message ?: "Error")
                }
            }
    }
}