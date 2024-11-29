package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.home

data class RxRetrofitTestState<out T>(val status:Status, val data: T?, val message:String?) {
    companion object {
        fun<T> loading(): RxRetrofitTestState<T> {
            return RxRetrofitTestState(Status.LOADING, null, null)
        }
        fun <T> success(data: T?) : RxRetrofitTestState<T> {
            return RxRetrofitTestState(Status.SUCCESS, data, null)
        }
        fun <T> error(msg: String?): RxRetrofitTestState<T> {
            return RxRetrofitTestState(Status.ERROR, null, msg)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}