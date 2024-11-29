package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.data.di

import android.util.Log
import com.aio.kotlin.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(Constants.URLS.BASE_URL)
            .build()
    }


    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("123", "123")
                    .build()

                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()
    }

}