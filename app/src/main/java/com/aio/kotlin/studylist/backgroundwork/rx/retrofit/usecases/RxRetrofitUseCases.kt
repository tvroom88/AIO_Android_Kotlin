package com.aio.kotlin.studylist.backgroundwork.rx.retrofit.usecases

import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.dto.RxRetrofitTestDTO
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.repository.RxRetrofitTestRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RxRetrofitUseCases @Inject constructor(
    private val rxRetrofitTestRepository: RxRetrofitTestRepository
) {

    fun execute(): Single<List<RxRetrofitTestDTO>> {
        return rxRetrofitTestRepository.fetchAllRxRetrofitTestData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}


