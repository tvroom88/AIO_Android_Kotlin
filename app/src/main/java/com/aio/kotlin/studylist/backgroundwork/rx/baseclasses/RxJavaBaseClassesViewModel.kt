package com.aio.kotlin.studylist.backgroundwork.rx.baseclasses

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass.CompletableClass
import com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass.FlowableClass
import com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass.MaybeClass
import com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass.ObservableClass
import com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass.SingleClass
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * RxJava 3.x 버전의 5개의 base classes 예제다.
 * Observable, Single, Maybe, Flowable, Completable
 */
class RxJavaBaseClassesViewModel : ViewModel() {


    // Observable Class LiveData
    private val _rxStatus1 = MutableLiveData<String>()  // 현재 상태 : onNext, onComplete를 나타내줌
    val rxStatus1: LiveData<String> get() = _rxStatus1
    private val _rxTimer1 = MutableLiveData<String>()   // Timer : 0, 1, 2, 3, 4
    val rxTimer1: LiveData<String> get() = _rxTimer1

    // Single Class LiveData
    private val _rxStatus2 = MutableLiveData<String>()  // 현재 상태 : onNext, onComplete를 나타내줌
    val rxStatus2: LiveData<String> get() = _rxStatus2

    // Single Class LiveData
    private val _rxStatus3 = MutableLiveData<String>()  // 현재 상태 : onNext, onComplete를 나타내줌
    val rxStatus3: LiveData<String> get() = _rxStatus3

    private val _rxStatus4 = MutableLiveData<String>()  // 현재 상태 : onNext, onComplete를 나타내줌
    val rxStatus4: LiveData<String> get() = _rxStatus4

    private val _rxStatus5 = MutableLiveData<String>()  // 현재 상태 : onNext, onComplete를 나타내줌
    val rxStatus5: LiveData<String> get() = _rxStatus5
    private val _rxTimer5 = MutableLiveData<String>()
    val rxTimer5: LiveData<String> get() = _rxTimer5


    private var disposable1: Disposable? = null
    private var disposable2: Disposable? = null
    private var disposable3: Disposable? = null
    private var disposable4: Disposable? = null
    private var disposable5: Disposable? = null


    init {
        // 초기 데이터 설정
        _rxTimer1.value = "Timer 0"
        _rxTimer5.value = "Timer 0"

        _rxStatus1.value = "아직 시작하지 전입니다."
        _rxStatus2.value = "아직 시작하지 전입니다."
        _rxStatus3.value = "아직 시작하지 전입니다."
        _rxStatus4.value = "아직 시작하지 전입니다."
        _rxStatus5.value = "아직 시작하지 전입니다."
    }

    /**
     * 1. Observable class
     * 데이터 발행자 : Observable
     * 데이터 수신자 (Observer) : Observer
     */
    fun rxJavaObservableClass() {
        val observableClass = ObservableClass()
        val observer = observableClass.createObserver(_rxStatus1, _rxTimer1)
        val observable = observableClass.createObservable()
        disposable1 = observableClass.getDisposable()

        observable
            .subscribeOn(Schedulers.io()) // 백그라운드 작업은 IO 스레드에서
            .observeOn(AndroidSchedulers.mainThread()) // 결과는 메인 스레드에서 관찰
            .subscribe(observer)
    }

    /**
     * 2. Single class
     * 데이터 발행자 : Single
     * 데이터 수신자 (Observer) : SingleObserver
     */
    @SuppressLint("CheckResult")
    fun rxJavaSingleClass() {
        val singleClass = SingleClass()
        val singleObserver: SingleObserver<Int> =
            singleClass.createSingleObserver(_rxStatus2)
        val single: Single<Int> = singleClass.createSingle()
        disposable2 = singleClass.getDisposable()
        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(singleObserver)

        Log.d("aaaaa", "dispose : ${disposable2?.isDisposed?.not()}")
    }


    /**
     * 3. Maybe class
     * 데이터 발행자 :Maybe
     * 데이터 수신자 (Observer) : MaybeObserver
     */
    @SuppressLint("CheckResult")
    fun rxJavaMaybeClass() {
        val maybeClass = MaybeClass()
        val maybeObserver: MaybeObserver<Int> = maybeClass.createMaybeObserver(_rxStatus3)
        val maybe: Maybe<Int> = maybeClass.createMaybe()
        disposable3 = maybeClass.getDisposable()
        maybe.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(maybeObserver)

        Log.d("aaaaa", "dispose : ${disposable2?.isDisposed?.not()}")
    }


    /**
     * 4. Completable class
     * 데이터 발행자 : Completable
     * 데이터 수신자 (Observer) : CompletableObserver
     */
    @SuppressLint("CheckResult")
    fun rxJavaCompletableClass() {
        val completableClass = CompletableClass()
        val completableObserver: CompletableObserver = completableClass.createCompletableObserver(_rxStatus4)
        val completable: Completable = completableClass.createCompletable()
        disposable4 = completableClass.getDisposable()
        completable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(completableObserver)

        Log.d("aaaaa", "dispose : ${disposable2?.isDisposed?.not()}")
    }


    /**
     * 5. Flowable class
     * 데이터 발행자 : Flowable
     * 데이터 수신자 (Observer) : Observer
     */
    fun rxJavaFlowableClass() {
        val flowableClass = FlowableClass()
        val observer = flowableClass.createDisposableSubscriber(_rxStatus5, _rxTimer5)
        val flowable = flowableClass.createFlowable()
        disposable5 = flowableClass.getDisposable()
        flowable
            .subscribeOn(Schedulers.io()) // 백그라운드 작업은 IO 스레드에서
            .observeOn(AndroidSchedulers.mainThread()) // 결과는 메인 스레드에서 관찰
            .subscribe(observer)
    }


    fun disposableDispose() {
        disposable1?.dispose()
        disposable2?.dispose()
        disposable3?.dispose()
        disposable4?.dispose()
        disposable5?.dispose()
    }
}