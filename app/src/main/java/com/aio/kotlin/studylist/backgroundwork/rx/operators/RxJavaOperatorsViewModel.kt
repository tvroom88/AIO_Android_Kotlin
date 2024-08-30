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
     * 1. 생성 연산자 : creating observables
     * just, create, interval, range, timer, intervalRange, defer, repeat
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