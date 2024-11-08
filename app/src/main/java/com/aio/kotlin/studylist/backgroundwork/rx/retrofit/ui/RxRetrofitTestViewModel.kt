package com.aio.kotlin.studylist.backgroundwork.rx.retrofit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.dto.RxRetrofitTestDTO
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.repository.RxRetrofitTestRepository
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.usecases.RxRetrofitUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class RxRetrofitTestViewModel @Inject constructor(private val rxRetrofitUseCases: RxRetrofitUseCases) :
    ViewModel() {

    private val _rxRetrofitTestData =
        MutableLiveData<RxRetrofitTestState<List<RxRetrofitTestDTO>>>()
    val rxRetrofitTestData: LiveData<RxRetrofitTestState<List<RxRetrofitTestDTO>>> get() = _rxRetrofitTestData

    private var disposable: Disposable? = null

    fun fetchAllData() {
        _rxRetrofitTestData.value = RxRetrofitTestState.loading()
        val observer = rxRetrofitUseCases.execute()
        disposable = observer.subscribe(
            { data -> _rxRetrofitTestData.value = RxRetrofitTestState.success(data) },
            { error -> _rxRetrofitTestData.value = RxRetrofitTestState.error(error.message) }
        )
    }

    fun dispose(){
        disposable?.dispose()
    }
}