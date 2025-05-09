package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.datasource.remote.retrofit

import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.Constants
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.datasource.remote.GithubServiceApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteRetrofitInstance {
    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private val gsonConverterFactory: GsonConverterFactory by lazy {
        GsonConverterFactory.create()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)    // Logcat에서 패킷 내용을 로그로 남기는 속성
            .baseUrl(Constants.GITHUB_API)
            .build()
    }

    val retrofitService: GithubServiceApi by lazy {
        retrofit.create(GithubServiceApi::class.java)
    }
}
