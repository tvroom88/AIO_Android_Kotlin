package com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable

/**
 * 앞에서 설명한 Observable과 달리 한 개의 데이터 또는 에러를 발행합니다, 때문에 네트워크 통신 등에서 자주 유용하게 사용합니다
 */
class SingleClass {

    var singleDisposable: Disposable? = null

    // Single 생성
    fun createSingle(): Single<Int> {
        return Single
            .create { emitter ->
                try {
                    if (!emitter.isDisposed) {
                        emitter.onSuccess(1)
                    }
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
    }

    // SingleObserver 생성
    fun createSingleObserver(
        rxStatus2: MutableLiveData<String>
    ): SingleObserver<Int> {
        return object : SingleObserver<Int> {
            override fun onSubscribe(d: Disposable) {
                singleDisposable = d
            }

            override fun onError(e: Throwable) {
                rxStatus2.postValue("오류입니다. 이유는 ${e.message}")
            }

            override fun onSuccess(t: Int) {
                rxStatus2.postValue("onSuccess 호출되었습니다. 전달받은 데이터는 $t 입니다.")
            }
        }
    }

    fun getDisposable(): Disposable? {
        return singleDisposable
    }
}