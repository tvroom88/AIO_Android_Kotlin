package com.aio.kotlin.studylist.backgroundwork.coroutine.stateflow

data class CoroutineUiState<out T>(val status: CoroutineStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> loading(): CoroutineUiState<T> {
            return CoroutineUiState(CoroutineStatus.LOADING, null, null)
        }

        fun <T> success(data: T): CoroutineUiState<T> {
            return CoroutineUiState(CoroutineStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String): CoroutineUiState<T> {
            return CoroutineUiState(CoroutineStatus.ERROR, null, msg)
        }

    }
}

enum class CoroutineStatus {
    LOADING,
    SUCCESS,
    ERROR
}
