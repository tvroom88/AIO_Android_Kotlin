package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate


data class CoroutinesTestState<out T>(val status: CoroutineStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> loading(): CoroutinesTestState<T> {
            return CoroutinesTestState(CoroutineStatus.LOADING, null, null)
        }

        fun <T> success(data: T): CoroutinesTestState<T> {
            return CoroutinesTestState(CoroutineStatus.SUCCESS, data, null)
        }

        fun <T> initialize(): CoroutinesTestState<T> {
            return CoroutinesTestState(CoroutineStatus.RELOAD, null, null)
        }

        fun <T> error(msg: String): CoroutinesTestState<T> {
            return CoroutinesTestState(CoroutineStatus.ERROR, null, msg)
        }

    }
}

enum class CoroutineStatus {
    LOADING,
    SUCCESS,
    ERROR,
    RELOAD
}
