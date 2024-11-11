package com.aio.kotlin.studylist.backgroundwork.rx.retrofit.repository

import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.api.RxRetrofitTestApi
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.dto.RxRetrofitTestDTO
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RxRetrofitTestRepository @Inject constructor(
    private val rxRetrofitTestApi: RxRetrofitTestApi
){
    fun fetchAllRxRetrofitTestData(): Single<List<RxRetrofitTestDTO>> {
        return rxRetrofitTestApi.getRxRetrofitTestData()
    }
}