package com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subscribers.DisposableSubscriber

/**
 * Flowable : 많은 양의 데이터 스트림을 다룰 때 사용되며, BackPressure 전략이 제공된다.
 * 소비자가 생성된 데이터를 충분히 빠르게 처리하지 못할 때 발생하는 문제를 관리하는 매커니즘이다.
 */
class FlowableClass {
    private var flowableDisposable: Disposable? = null

    // Completable 생성
    fun createFlowable(): Flowable<Int> {
        return Flowable.create({ emitter ->
            try {
                for (message in 1..5) {
                    if (!emitter.isCancelled) {
                        emitter.onNext(message)
                        Thread.sleep(1000) // 1초 지연
                    }
                }
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)
    }

    // SingleObserver 생성
    // Observer 생성
    fun createDisposableSubscriber(
        rxStatus1: MutableLiveData<String>,
        rxTimer1: MutableLiveData<String>
    ): DisposableSubscriber<Int> {
        return object : DisposableSubscriber<Int>() {
            override fun onNext(t: Int) {
                rxStatus1.postValue("onNext 진행중입니다.")
                rxTimer1.postValue("Timer : $t")            }

            override fun onError(t: Throwable) {
                rxStatus1.postValue("onError 도달하였습니다. error : ${t.message}")

            }

            override fun onComplete() {
                rxStatus1.postValue("onComplete에 도달하였습니다.")
            }

        }
    }
    fun getDisposable(): Disposable? {
        return flowableDisposable
    }
}