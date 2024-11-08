package com.aio.kotlin.studylist.backgroundwork.rx.retrofit.api

import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.dto.RxRetrofitTestDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST


interface RxRetrofitTestApi {

    @GET("posts")  // 데이터를 한번만 받아오기 때문에 Single을 사용한다.
    fun getRxRetrofitTestData(): Single<List<RxRetrofitTestDTO>>
}