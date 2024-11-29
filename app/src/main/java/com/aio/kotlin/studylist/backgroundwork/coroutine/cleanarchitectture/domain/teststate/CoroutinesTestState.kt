package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate


class CoroutinesTestState<out T>(val status: CoroutineStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): CoroutinesTestState<T> {
            return CoroutinesTestState(CoroutineStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String): CoroutinesTestState<T> {
            return CoroutinesTestState(CoroutineStatus.ERROR, null, msg)
        }
    }
}

enum class CoroutineStatus {
    SUCCESS,
    ERROR,
}
