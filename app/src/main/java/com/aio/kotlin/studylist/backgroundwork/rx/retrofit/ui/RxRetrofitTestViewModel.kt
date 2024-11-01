package com.aio.kotlin.studylist.backgroundwork.rx.retrofit.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.dto.RxRetrofitTestDTO
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.repository.RxRetrofitTestRepository
import io.reactivex.rxjava3.disposables.Disposable

class RxRetrofitTestViewModel : ViewModel() {

    private val _rxRetrofitTestData =
        MutableLiveData<RxRetrofitTestState<List<RxRetrofitTestDTO>>>()
    val rxRetrofitTestData: LiveData<RxRetrofitTestState<List<RxRetrofitTestDTO>>> get() = _rxRetrofitTestData

    private var rxRetrofitRestRepository: RxRetrofitTestRepository = RxRetrofitTestRepository()
    private var disposable: Disposable? = null

    fun fetchAllData() {
        _rxRetrofitTestData.value = RxRetrofitTestState.loading()
        val observer = rxRetrofitRestRepository.fetchAllRxRetrofitTestData()
        disposable = observer.subscribe(
            { data -> _rxRetrofitTestData.value = RxRetrofitTestState.success(data)},
            { error ->_rxRetrofitTestData.value = RxRetrofitTestState.error(error.message) }
        )
    }
}