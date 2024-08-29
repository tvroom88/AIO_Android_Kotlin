package com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Completable은 완료와 에러만 보내는 특수한 형태의 스트림입니다 (정말 간단하지요?;)
 *
 * onComplete() : 데이터 발행이 끝났을 때 호출된다
 * onError() : 오류가 발생했을 때 호출된다
 *
 */
class CompletableClass {

    var completableDisposable: Disposable? = null

    // Completable 생성
    fun createCompletable(): Completable {
        return Completable.create { emitter ->
            try {
                if (!emitter.isDisposed) {
                    emitter.onComplete() // 1번만 호출되고 더이상 호출되지 않는다.
                }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    // SingleObserver 생성
    fun createCompletableObserver(
        rxStatus4: MutableLiveData<String>
    ): CompletableObserver {
        return object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
                completableDisposable = d
            }

            override fun onError(e: Throwable) {
                rxStatus4.postValue("onError 호출 되었습니다.")
            }

            override fun onComplete() {
                rxStatus4.postValue("onComplete 호출 되었습니다. 데이터 없이 완료 에러만 방출합니다.")
                Log.d("Completable", "onComplete")
            }
        }
    }

    fun getDisposable(): Disposable? {
        return completableDisposable
    }
}