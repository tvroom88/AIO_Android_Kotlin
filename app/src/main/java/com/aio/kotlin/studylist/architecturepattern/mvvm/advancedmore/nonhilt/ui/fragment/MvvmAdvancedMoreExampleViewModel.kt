package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui.fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.model.Pokemon
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.PokemonUiState
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.PokemonUiStatus
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.GithubRepository
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.entity.remote.RemoteGithubModel
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MvvmAdvancedMoreExampleViewModel(private val githubRepository: GithubRepository) :
    ViewModel() {

    private val _pokemonList: MutableStateFlow<PokemonUiState<List<Pokemon>>> =
        MutableStateFlow(PokemonUiState.initialize())
    val pokemonList: StateFlow<PokemonUiState<List<Pokemon>>> = _pokemonList
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            PokemonUiState.initialize()
        )


    private val _githubResponse: MutableStateFlow<UiState<List<RemoteGithubModel>>> =
        MutableStateFlow(UiState.INIT)
    val githubResponse: StateFlow<UiState<List<RemoteGithubModel>>> = _githubResponse
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            UiState.INIT
        )

    private val _githubFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val githubFetchingIndex = _githubFetchingIndex

    fun fetchGithubData(since: Int, perPage: Int) = viewModelScope.launch {
        githubRepository.getGithubResponse(since, perPage)
            .onStart {
                _githubResponse.value = UiState.Loading
            }.catch {
                Log.d("GithubGithub", "fetchGithubData : catch")
                _githubResponse.value = UiState.Error(it.message ?: "Error")
            }.collectLatest { result ->
                result.onSuccess { data ->
                    Log.d("GithubGithub", "fetchGithubData : collectLatest : success")
                    _githubResponse.value = UiState.Success(data)
                }.onFailure {
                    Log.d("GithubGithub", "fetchGithubData : collectLatest : failure")
                    _githubResponse.value = UiState.Error(it.message ?: "Error")
                }
            }
    }

    fun fetchNextGithubData() {
        if (_pokemonList.value.status != PokemonUiStatus.LOADING) {
            _githubFetchingIndex.value++
        }
    }
}