package com.aio.kotlin.studylist.backgroundwork.rx.basic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private val _rxLiveData3 = MutableLiveData<String>()
    val rxLiveData3: LiveData<String> get() = _rxLiveData3

    init {
        // 초기 데이터 설정
        _rxLiveData1.value = "Start Timer with Observalbe & Zip & Just"
        _rxLiveData2.value = "Start Timer with Observalbe & Zip & Range"
        _rxLiveData3.value = "Sample Rx Data3"
    }


    /**
     * Class : Observable
     * Operator : zip(), just(), interval()
     */
    fun rxJavaExample1() {
        val disposable1: Disposable = Flowable.just("Hello, World").subscribe(::println)
        val disposable2 = Observable
            .zip(
                Observable.just("1", "2", "3", "4", "5"),
                Observable.interval(1, TimeUnit.SECONDS)
            ) { item, _ -> item } // 각 항목을 그대로 반환
            .subscribe({ s ->
                _rxLiveData1.postValue(s)
            }, { throwable ->
                throwable.printStackTrace()  // 에러 처리 (선택 사항)
            }, {
                _rxLiveData1.postValue("Timer is finished. Thank you")
            })
    }

    /**
     * Observable : Observable
     * Observer : Observer
     * Operator : zip(), just(), interval()
     * Scheduler : Schedulers.computation(), Schedulers.io()
     */
    fun rxJavaExample2() {
        var disposable: Disposable? = null
        val observable = Observable
            .zip(
                Observable.range(1, 5),
                Observable.interval(1, TimeUnit.SECONDS)
            ) { item, _ -> item } // 각 항목을 그대로 반환

        // Observer 정의
        val observer: Observer<Int> = object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(number: Int) {
                _rxLiveData2.postValue(number.toString())

            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
                _rxLiveData2.postValue("Timer is finished. Thank you")
                disposable?.apply {
                    if (!isDisposed) {
                        disposable?.dispose()
                    }
                }
            }
        }

        // Observable 구독 및 Scheduler 적용
        observable
            .subscribeOn(Schedulers.computation()) // Observable의 연산을 계산 스레드에서 실행
            .observeOn(Schedulers.io()) // Observer의 콜백을 IO 스레드에서 실행
            .subscribe(observer)
    }


    /**
     * Observable : Observable
     * Observer : DisposableObserver
     * Scheduler : Schedulers.computation(), Schedulers.io()
     */
    fun rxJavaExample3() {
        var disposable: Disposable? = null
        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(s: String) {
                _rxLiveData3.postValue(s)
            }

            override fun onError(e: Throwable) {
                Log.d("error", e.toString())
            }

            override fun onComplete() {
                _rxLiveData3.postValue("Timer is finished. Thank you")

                disposable?.apply {
                    if (!isDisposed) {
                        disposable?.dispose()
                    }
                }

            }
        }

        val disposableObserver = object : DisposableObserver<String>() {
            override fun onNext(s: String) {
                _rxLiveData3.postValue(s)
            }

            override fun onError(e: Throwable) {}
            override fun onComplete() {
                _rxLiveData3.postValue("Timer is finished. Thank you")
            }
        }

        val observable = Observable.create { emitter ->
            val messages = listOf("5", "4", "3", "2", "1")
            for (message in messages) {
                if (!emitter.isDisposed) {
                    Thread.sleep(1000) // 1초 지연
                    emitter.onNext(message)
                }
            }
            emitter.onComplete()
        }

        observable
            .subscribeOn(Schedulers.io())        // Observable이 동작을 시작할 스레드"를 지정한다.
            .observeOn(Schedulers.computation()) // Computation 스케줄러를 사용하여 데이터 처리
            .subscribe(disposableObserver)
    }

}