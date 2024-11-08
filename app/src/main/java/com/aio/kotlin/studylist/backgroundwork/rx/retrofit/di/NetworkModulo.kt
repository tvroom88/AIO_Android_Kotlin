package com.aio.kotlin.studylist.backgroundwork.rx.retrofit.di

import com.aio.kotlin.constants.Constants
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.api.RxRetrofitTestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModulo {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(Constants.URLS.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RxRetrofitTestApi {
        return retrofit.create(RxRetrofitTestApi::class.java)
    }
}