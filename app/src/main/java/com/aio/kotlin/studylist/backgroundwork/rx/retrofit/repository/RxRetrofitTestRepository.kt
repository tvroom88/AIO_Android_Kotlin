package com.aio.kotlin.studylist.backgroundwork.rx.retrofit.repository

import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.api.RxRetrofitTestApi
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.dto.RxRetrofitTestDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RxRetrofitTestRepository {

    fun fetchAllRxRetrofitTestData(): Single<List<RxRetrofitTestDTO>> {
        val rxRetrofitTestApi = RxRetrofitTestApi.create()
        return rxRetrofitTestApi.getTestSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}