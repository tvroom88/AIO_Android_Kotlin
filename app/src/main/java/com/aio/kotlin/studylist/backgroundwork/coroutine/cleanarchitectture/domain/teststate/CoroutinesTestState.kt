package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate

import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.ui.Status

class CoroutinesTestState<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): CoroutinesTestState<T> {
            return CoroutinesTestState(Status.SUCCESS, data, null)
        }

        fun <T> empty(): CoroutinesTestState<T> {
            return CoroutinesTestState(Status.SUCCESS, null, null)
        }

        fun <T> error(msg: String?): CoroutinesTestState<T> {
            return CoroutinesTestState(Status.ERROR, null, msg)
        }

        fun <T> loading(): CoroutinesTestState<T> {
            return CoroutinesTestState(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
