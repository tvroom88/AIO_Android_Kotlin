package com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Observable : 데이터 흐름에 맞게 알림을 보내줘서 구독한 Observer이 데이터를 사용할 수 있도록 해준다.
 * 소량의 데이터 스트림을 다룰 때 사용된다. BackPressure 지원 안함
 */
class ObservableClass {

    var observableDisposable: Disposable? = null

    // Observable 생성
    fun createObservable(): Observable<Int> {
        return Observable
            .create { emitter ->
                try {
                    for (message in 0..5) {
                        if (!emitter.isDisposed) {
                            emitter.onNext(message)
                            Thread.sleep(1000) // 1초 지연
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
                rxStatus1.postValue("onNext 진행중입니다.")
                rxTimer1.postValue("Timer : $t")
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
