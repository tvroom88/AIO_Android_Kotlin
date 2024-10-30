package com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.fiveclass

import android.database.sqlite.SQLiteConstraintException
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
                    if(saveDataInLocal("Toms")){
                        emitter.onComplete() // 1번만 호출되고 더이상 호출되지 않는다.
                    }
                }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    /**
     * 아래 상황을 가정
     * 데이터베이스 충돌 또는 제약 조건 위반
     * SQLite 데이터베이스에서 UNIQUE 또는 NOT NULL 등의 제약 조건이 있는 경우,
     * 데이터가 해당 조건을 충족하지 않으면 저장에 실패할 수 있습니다.
     * 이 경우 SQLiteConstraintException이 발생할 수 있습니다.
     */
    private fun saveDataInLocal(username: String) : Boolean{
        val nameList = mutableListOf("Tom", "John")
        if(nameList.contains(username)){
            throw SQLiteConstraintException("Username '$username' already exists in the database.")
        }
        nameList.add(username)
        return true
    }

    // CompletableObserver 생성
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
                rxStatus4.postValue("onComplete 호출 되었습니다. 데이터가 정상적으로 처리되었습니다.")
                Log.d("Completable", "onComplete")
            }
        }
    }

    fun getDisposable(): Disposable? {
        return completableDisposable
    }
}