package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.model.Pokemon
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.repository.PokemonRepository
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.PokemonUiState
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.PokemonUiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MvvmAdvancedViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    // 포켓몬 리스트
    private val _pokemonList: MutableStateFlow<PokemonUiState<List<Pokemon>>> =
        MutableStateFlow(PokemonUiState.initialize())
    val pokemonList: StateFlow<PokemonUiState<List<Pokemon>>> = _pokemonList
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            PokemonUiState.initialize()
        )


    private val _pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val pokemonFetchingIndex = _pokemonFetchingIndex

    fun fetchPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.fetchPokemonList(pokemonFetchingIndex.value)
                .onStart {
                    _pokemonList.value = PokemonUiState.loading()
                }.catch {
                    _pokemonList.value = PokemonUiState.error(it.message ?: "catch error")
                }.collect { result ->
                    result.onSuccess {
                        _pokemonList.value = PokemonUiState.success(it)
                    }.onFailure {
                        _pokemonList.value = PokemonUiState.error(it.message ?: "on failure error")
                    }
                }
        }
    }

    fun fetchNextPokemonList(){
        if(_pokemonList.value.status != PokemonUiStatus.LOADING){
            pokemonFetchingIndex.value++
        }
    }
}
