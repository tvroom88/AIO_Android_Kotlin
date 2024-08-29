package com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subscribers.DisposableSubscriber

class FlowableClass {
    private var flowableDisposable: Disposable? = null

    // Completable 생성
    fun createFlowable(): Flowable<Int> {
        return Flowable.create({ emitter ->
            try {
                for (message in 1..5) {
                    if (!emitter.isCancelled) {
                        Thread.sleep(1000) // 1초 지연
                        emitter.onNext(message)
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