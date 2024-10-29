package com.aio.kotlin.studylist.backgroundwork.rx.basic

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Observerables
 * Create, Defer
 */

class RxJavaBasicViewModel : ViewModel() {

    // LiveData 변수 선언
    private val _rxLiveData1 = MutableLiveData<String>()
    val rxLiveData1: LiveData<String> get() = _rxLiveData1

    private val _rxLiveData2 = MutableLiveData<String>()
    val rxLiveData2: LiveData<String> get() = _rxLiveData2

    init {
        // 초기 데이터 설정
        _rxLiveData1.value = "예제 1 TextView 입니다."
        _rxLiveData2.value = "예제 2 TextView 입니다."
    }

    /**
     * 1. Hello, World 예제
     * Class : Observable
     * Operator : create
     */
    fun rxJavaExample1() {

        // 아주 간단하게
        val disposable1: Disposable = Flowable.just("Hello, World").subscribe(::println)

        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: String) {
                _rxLiveData1.value = t
            }

            override fun onError(e: Throwable) {}

            override fun onComplete() {}
        }

        val observable = Observable.create { emitter ->
            emitter.onNext("Hello, World")
        }

        observable
            .subscribeOn(Schedulers.io()) // Observable의 연산을 계산 스레드에서 실행
            .observeOn(AndroidSchedulers.mainThread())         // Observer의 콜백을 IO 스레드에서 실행
            .subscribe(observer)
    }


    /**
     * 2. 0부터 5까지 숫자 세는 예제
     * Class : Observable
     * Operator : create
     */
    @SuppressLint("CheckResult")
    fun rxJavaExample2() {
        Observable.create { emitter ->
            for (num in 0..5) {
                emitter.onNext("num : $num")
                Thread.sleep(1000) // 1초 지연
            }
            emitter.onComplete()
        }.subscribeOn(Schedulers.io()) // Observable의 연산을 계산 스레드에서 실행
            .observeOn(AndroidSchedulers.mainThread()) // Observer의 콜백을 IO 스레드에서 실행
            .subscribe({
                _rxLiveData2.value = it  // onNext
            }, {
                _rxLiveData2.value = it.toString() // onError
            }, {
                _rxLiveData2.value = "Complete Counting from 0 to 5" // onComplete
            })
    }

}