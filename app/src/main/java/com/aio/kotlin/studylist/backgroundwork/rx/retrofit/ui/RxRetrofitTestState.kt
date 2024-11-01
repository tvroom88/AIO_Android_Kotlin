package com.aio.kotlin.studylist.backgroundwork.rx.retrofit.ui

data class RxRetrofitTestState<out T>(val status:Status, val data: T?, val message:String?) {
    companion object {
        fun <T> success(data: T?) : RxRetrofitTestState<T> {
            return RxRetrofitTestState(Status.SUCCESS, data, null)
        }

        fun <T> empty(): RxRetrofitTestState<T> {
            return RxRetrofitTestState(Status.SUCCESS, null, null)
        }

        fun <T> error(msg: String?): RxRetrofitTestState<T> {
            return RxRetrofitTestState(Status.ERROR, null, msg)
        }

        fun<T> loading(): RxRetrofitTestState<T> {
            return RxRetrofitTestState(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}