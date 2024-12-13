package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.di

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
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonNetworkModulo {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PokemonRetrofit

    @PokemonRetrofit
    @Singleton
    @Provides
    fun provideRetrofit( @Named("PokemonOkHttp") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(Constants.URLS.POKEMON_BASE_URL)
            .build()
    }

    @Named("PokemonOkHttp")
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
                    .build()

                Log.d("urlurl", url.toUri().toString())

                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()
    }
}