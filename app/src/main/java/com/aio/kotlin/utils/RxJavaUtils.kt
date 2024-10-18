package com.aio.kotlin.utils

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class RxJavaUtils {

    private fun makeObserver(
        onNextFun: List<() -> Unit>?,
        onErrorFun: List<() -> Unit>?,
        onCompleteFun: List<() -> Unit>?
    ): Observer<Int> {
        return object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                Log.d("Observer", "onSubscribe")
            }

            override fun onNext(t: Int) {
                Log.d("Observer", "onNext : $t")
                if (onNextFun != null) {
                    for (nextFun in onNextFun) {
                        nextFun
                    }
                }
            }

            override fun onError(e: Throwable) {
                Log.d("Observer", "onError")
                if (onErrorFun != null) {
                    for (errorFun in onErrorFun) {
                        errorFun
                    }
                }
            }

            override fun onComplete() {
                Log.d("Observer", "onComplete")
                if (onCompleteFun != null) {
                    for (completeFun in onCompleteFun) {
                        completeFun
                    }
                }
            }
        }
    }
}