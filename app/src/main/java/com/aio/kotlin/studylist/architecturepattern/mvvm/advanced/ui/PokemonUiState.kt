package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui


data class PokemonUiState<out T>(val status: PokemonUiStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> loading(): PokemonUiState<T> {
            return PokemonUiState(PokemonUiStatus.LOADING, null, null)
        }

        fun <T> success(data: T): PokemonUiState<T> {
            return PokemonUiState(PokemonUiStatus.SUCCESS, data, null)
        }

        fun <T> initialize(): PokemonUiState<T> {
            return PokemonUiState(PokemonUiStatus.RELOAD, null, null)
        }

        fun <T> error(msg: String): PokemonUiState<T> {
            return PokemonUiState(PokemonUiStatus.ERROR, null, msg)
        }
    }
}

enum class PokemonUiStatus {
    LOADING,
    SUCCESS,
    ERROR,
    RELOAD
}
