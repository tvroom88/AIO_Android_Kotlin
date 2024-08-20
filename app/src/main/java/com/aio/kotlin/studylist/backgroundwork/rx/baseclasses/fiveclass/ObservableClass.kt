package com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class ObservableClass {

    var observableDisposable: Disposable? = null

    // Observable 생성
    fun createObservable(): Observable<Int> {
        return Observable
            .create { emitter ->
                try {
                    for (message in 1..5) {
                        if (!emitter.isDisposed) {
                            Thread.sleep(1000) // 1초 지연
                            Log.d("aaaa", "$message")
                            emitter.onNext(message)
                        }
                    }
                    emitter.onComplete()

                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
    }

    // Observer 생성
    fun createObserver(
        rxStatus1: MutableLiveData<String>,
        rxTimer1: MutableLiveData<String>
    ): Observer<Int> {
        return object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                Log.d("Observer", "onSubscribe")
                observableDisposable = d
            }

            override fun onNext(t: Int) {
                Log.d("Observer", "onNext : $t")
//                rxStatus1.postValue("onNext 진행중입니다.")
//                rxTimer1.postValue("Timer : $t")
                rxTimer1.value = "Timer : $t"

            }

            override fun onError(e: Throwable) {
                Log.d("Observer", "onError")
                rxStatus1.postValue("onError 입니다.")
            }

            override fun onComplete() {
                Log.d("Observer", "onComplete")
                rxStatus1.postValue("onComplete에 도달하였습니다..")
            }
        }
    }

    fun getDisposable(): Disposable? {
        return observableDisposable
    }
}
