package com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable

class SingleClass {

    var singleDisposable: Disposable? = null

    // Single 생성
    fun createSingle(): Single<Int> {
        return Single
            .create { emitter ->
                try {
                    for (message in 1..5) {
                        if (!emitter.isDisposed) {
                            Thread.sleep(1000) // 1초 지연
                            emitter.onSuccess(message)
                        }
                    }

                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
    }

    // SingleObserver 생성
    fun createSingleObserver(
        rxStatus2: MutableLiveData<String>,
        rxTimer2: MutableLiveData<String>
    ): SingleObserver<Int> {
        return object : SingleObserver<Int> {
            override fun onSubscribe(d: Disposable) {
                singleDisposable = d
            }

            override fun onError(e: Throwable) {

            }

            override fun onSuccess(t: Int) {
                val statusString: String = if (t < 5) {
                    "onSuccess 진행중입니다."
                } else {
                    "onSuccess가 끝났습니다."
                }
                rxStatus2.postValue(statusString)
                rxTimer2.postValue("Timer : $t")
                Log.d("aaaa", "Timer : $t")
            }
        }
    }

    fun getDisposable(): Disposable? {
        return singleDisposable
    }
}