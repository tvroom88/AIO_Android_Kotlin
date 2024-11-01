package com.aio.kotlin.studylist.backgroundwork.rx.retrofit.api

import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.dto.RxRetrofitTestDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST


interface RxRetrofitTestApi {

    // 데이터를 한번만 받아오기 때문에 Single을 사용한다.
    @GET("posts")
    fun getTestSingle(): Single<List<RxRetrofitTestDTO>>


    companion object {
        private const val BASE_URL = " https://jsonplaceholder.typicode.com/"

        fun create(): RxRetrofitTestApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(RxRetrofitTestApi::class.java)
        }
    }
}