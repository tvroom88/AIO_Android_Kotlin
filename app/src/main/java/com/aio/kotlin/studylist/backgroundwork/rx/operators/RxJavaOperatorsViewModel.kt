package com.aio.kotlin.studylist.backgroundwork.rx.operators

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * 참고 url :
 * (1) https://reactivex.io/documentation/operators.html
 * (2) 연산자 종류 : https://smashandroid.tistory.com/71
 * (3) 생성 연산자 : https://smashandroid.tistory.com/73
 * (4) Throttle vs Debounce : https://velog.io/@thsamajiki/RxJava-debounce
 */
class RxJavaOperatorsViewModel : ViewModel() {

    // Creating Observables
    private val _rxJustLiveData = MutableLiveData<String>()
    val rxJustLiveData: LiveData<String> get() = _rxJustLiveData

    private val _rxCreateLiveData = MutableLiveData<String>()
    val rxCreateLiveData: LiveData<String> get() = _rxCreateLiveData

    private val _rxIntervalLiveData = MutableLiveData<String>()
    val rxIntervalLiveData: LiveData<String> get() = _rxIntervalLiveData

    // Transforming  Observables
    private val _rxMapLiveData = MutableLiveData<String>()
    val rxMapLiveData: LiveData<String> get() = _rxMapLiveData

    // Filter Observables (Debounce)
    private val _rxDebounceLiveData = MutableLiveData<String>()
    val rxDebounceLiveData: LiveData<String> get() = _rxDebounceLiveData

    // Filter Observables (Debounce)
    private val _rxThrottleLiveData = MutableLiveData<String>()
    val rxThrottleLiveData: LiveData<String> get() = _rxThrottleLiveData

    init {

        // Creating Observables
        _rxJustLiveData.value = "Just 예제 시작 전입니다."
        _rxCreateLiveData.value = "Create 예제 시작 전입니다."
        _rxIntervalLiveData.value = "Interval 예제 시작 전입니다."

        // Transforming  Observables
        _rxMapLiveData.value = "Map 예제 시작 전입니다."

        // Filtering Observables
        _rxDebounceLiveData.value = "Debounce 예제 시작 전입니다."
        _rxThrottleLiveData.value = "Throttle 예제 시작 전입니다."
    }

    /**
     *
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
            emitter.onComplete()
        }
        val result = source
            .subscribeOn(Schedulers.io()) // 데이터 생성은 IO 스레드에서 실행
            .observeOn(AndroidSchedulers.mainThread()) // 결과는 메인 스레드에서 관찰
            .subscribe {
                _rxCreateLiveData.value = it
            }
    }

    // interval : 시간 간격을 두고 데이터를 전달하는 stream을 만든다. 기본적으로 SchedulerSupport.COMPUTATION 스케쥴러로 별도의 스레드에서 실행 된다.
    fun operatorInterval() {
        val source =
            Observable.interval(1000L, TimeUnit.MILLISECONDS).map { data -> (data + 1) }.take(5)
        val result = source.observeOn(AndroidSchedulers.mainThread()) // 결과는 메인 스레드에서 관찰
            .subscribe {
                _rxIntervalLiveData.value = "$it"
            }
    }

    /**
     * 2. 변환연산자 : 입력을 받아서 원하는 출력을 내는 전통적인 의미의 함수 (Transforming Observables)
     * map(), flatmap(), reduce() 등등
     */

    // Transforming map()
    fun operatorMap() {
        val numberList = arrayOf("1", "2", "3", "4", "5")
        val source = Observable.fromArray(numberList).map { "number $it" }

        val result = source.observeOn(AndroidSchedulers.mainThread()) // 결과는 메인 스레드에서 관찰
            .subscribe {
                _rxMapLiveData.value = "$it"
            }
    }

    /**
     * 3. 필터연산자 Filtering Observables
     * filter(), take(), skip(), distinct(), Debounce(), Throttle
     */
    // Debounce : 이벤트를 그룹화하여 특정시간이 지난 후 하나의 이벤트만 발생하도록 하는 기술이다.
    // 예제 : 이벤트1 이벤트2 (— 4.9ms— ) 이벤트4 이벤트5(— 5ms — )
    fun operatorDebounce() {
        val numberList = arrayOf("1", "2", "3", "4", "5")

        val result = Observable.fromArray(*numberList) // 배열의 값을 하나씩 방출
            .concatMap { item ->
                // 각 아이템을 Observable로 감싸고 1초 지연
                Observable.just(item).delay(1001, TimeUnit.MILLISECONDS)
            }
            .debounce(1000, TimeUnit.MILLISECONDS) // 1초 동안 이벤트가 없으면 해당 아이템을 방출
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { item ->
                    _rxDebounceLiveData.postValue(item) // 방출된 값을 LiveData에 전달
                },
                { error ->
                    Log.e("operatorDebounce", "Error: ${error.message}")
                }
            )
    }

    // Throttle : 이벤트를 일정한 주기마다 발생하도록 하는 기술이다.
    // 예제 : 이벤트1 ( — 1ms —) 이벤트2 ( — 1ms — ) 이벤트3
    fun operatorThrottle() {

        val numberList = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        val source = Observable.create { emitter ->
            numberList.forEach {
                emitter.onNext(it)
                Thread.sleep(1000) // 1초 지연
            }
            emitter.onComplete()
        }
        val result = source

            .throttleFirst(2, TimeUnit.SECONDS) // 1초 간격으로 최신 이벤트만 방출
            .subscribeOn(Schedulers.io()) // 데이터 생성은 IO 스레드에서 실행
            .observeOn(AndroidSchedulers.mainThread()) // 결과는 메인 스레드에서 관찰
            .subscribe {
                _rxThrottleLiveData.value = it
            }
    }

    /**
     * 4. 결합 연산자 :
     * zip, Merge
     */
}