package com.aio.kotlin.studylist.backgroundwork.rx.operators

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * 참고 url :
 * (1) https://reactivex.io/documentation/operators.html
 */
class RxJavaOperatorsViewModel : ViewModel() {

    // LiveData
    private val _rxJustLiveData = MutableLiveData<String>()
    val rxJustLiveData: LiveData<String> get() = _rxJustLiveData

    private val _rxCreateLiveData = MutableLiveData<String>()
    val rxCreateLiveData: LiveData<String> get() = _rxCreateLiveData

    private val _rxIntervalLiveData = MutableLiveData<String>()
    val rxIntervalLiveData: LiveData<String> get() = _rxIntervalLiveData

    init {
        _rxJustLiveData.value = "Just 예제 시작 전입니다."
        _rxCreateLiveData.value = "Create 에제 시작 전입니다."
    }

    /**
     * https://smashandroid.tistory.com/73
     * 1. 생성 연산자 : creating observables
     * just, create, interval, range, timer, intervalRange, defer, repeat
     */

    // just : 인자를 받는 데이터를 순서대로 발행하는 Observable 생성 연산자이며, 최대 10개 까지 발행할 수 있다.
    fun operatorJust() {
        val source = Observable.just("1", "2", "3", "4", "5")
        var str = ""

        // 데이터를 소비하는 Observer를 직접 만들수 있지만 그것을 생략할 수도 있다.
        val result = source.observeOn(AndroidSchedulers.mainThread()) // 결과는 메인 스레드에서 관찰
            .subscribe(
                { // onNext
                    str = "$str $it"
                    _rxJustLiveData.value = "$str"
                    Log.d("RxJavaOperatorsViewModel", "operatorJust : $it")
                },
                { error -> error.printStackTrace() }, // onError: 에러 처리
                { _rxJustLiveData.value = "$str Complete" } // onComplete
            )
    }

    // create : emitter를 이용하여 데이터를 발행하고, onNext(), onComplete(), onError() 함수를 이용하여 데이터를 발행하거나 완료처리, 에러처리
    fun operatorCreate() {
        val userData = listOf("A - 20 years old", "B - 32 years old", "C - 40 years old")
        val source = Observable.create { emitter ->

            userData.forEachIndexed { idx, c ->
                emitter.onNext("$idx : $c")
                Thread.sleep(1000) // 1초 지연
            }

        }
        val result = source.observeOn(AndroidSchedulers.mainThread()) // 결과는 메인 스레드에서 관찰
            .subscribe {
                _rxCreateLiveData.value = it
            }
    }

    // interval : 시간 간격을 두고 데이터를 전달하는 stream을 만든다.
    fun operatorInterval() {
//        val source =
//            Observable.interval(1000L, TimeUnit.MILLISECONDS).map { data -> (data + 1) }
//        val result = source.observeOn(AndroidSchedulers.mainThread()) // 결과는 메인 스레드에서 관찰
//            .subscribe {
//
//            }
    }


}