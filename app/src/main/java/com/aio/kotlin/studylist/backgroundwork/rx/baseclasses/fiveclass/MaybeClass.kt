package com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Maybe는 최대로 발행할 수 있는 데이터가 하나입니다 때문에 데이터가 없이 완료될 수도 있습니다
 * Single과 다른 점은 onComplete(완료) 이벤트가 추가됩니다
 *
 * onSuccess : 데이터 하나를 발행하고 종료된다
 * onError() : 오류가 발생했을 때 호출된다
 * onComplete : 1건의 데이터 발행이 없이 데이터 발행이 완료됐을 때 호출된다
 */
class MaybeClass {

    var maybeDisposable: Disposable? = null

    // Single 생성
    fun createMaybe(): Maybe<Int> {
        Log.d("Maybe", "createMaybe")
        return Maybe
            .create { emitter ->
                try {
                    emitter.onSuccess(1)
                    emitter.onComplete() // Maybe class에서는 onSuccess 호출로 데이터를 방출하면 onComplete는 호출되지 않는다.
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
    }

    // SingleObserver 생성
    fun createMaybeObserver(
        rxStatus3: MutableLiveData<String>
    ): MaybeObserver<Int> {
        return object : MaybeObserver<Int> {
            override fun onSubscribe(d: Disposable) {
                maybeDisposable = d
            }

            override fun onError(e: Throwable) {

            }

            override fun onSuccess(t: Int) {
                rxStatus3.postValue("onSuccess 호출되었습니다. 전달 받은 데이터는 $t 입니다.")
            }

            override fun onComplete() {
                rxStatus3.postValue("onComplete 방출후 완료되었습니다.")
                Log.d("Maybe", "onComplete")
            }
        }
    }

    fun getDisposable(): Disposable? {
        return maybeDisposable
    }
}