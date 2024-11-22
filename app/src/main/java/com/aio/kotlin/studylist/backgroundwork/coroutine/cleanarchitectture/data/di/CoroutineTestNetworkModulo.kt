package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.di

import com.aio.kotlin.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineTestNetworkModulo {

    // RxJava 예제에서도 Retrofit에 Dependency Injection을 해서 이렇게 나눠준단다. 혹시나 2개 이상 사용하지 않는다면 굳이 이렇게 할 필요는 없다.
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CoroutineTestRetrofit

    @CoroutineTestRetrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(Constants.URLS.BASE_URL)
            .build()
    }

}