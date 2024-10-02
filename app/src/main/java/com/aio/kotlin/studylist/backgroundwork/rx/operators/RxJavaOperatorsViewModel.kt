package com.aio.kotlin.studylist.backgroundwork.rx.operators

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
    private val _rxCreatingLiveData = MutableLiveData<String>()
    val rxCreatingLiveData: LiveData<String> get() = _rxCreatingLiveData


    init {
        _rxCreatingLiveData.value = "아직 시작하지 않았습니다."
    }

    /**
     * https://smashandroid.tistory.com/73
     * 1. 생성 연산자 : creating observables
     * just, create, interval, range, timer, intervalRange, defer, repeat
     *
     * just : 인자를 받는 데이터를 순서대로 발행하는 Observable
     * create : emitter를 이용하여 데이터를 발행하고, onNext(), onComplete(), onError() 함수를 이용하여 데이터를 발행하거나 완료처리, 에러처리
     * interval : 시간 간격을 두고 데이터를 전달하는 stream을 만든다.
     */
    fun makeObservable() {

        val source =
            Observable.interval(1000L, TimeUnit.MILLISECONDS).map { data -> (data + 1) }
        val a = source.observeOn(AndroidSchedulers.mainThread()) // 결과는 메인 스레드에서 관찰
            .subscribe { it ->
                _rxCreatingLiveData.value = it.toString()
            }
    }

}