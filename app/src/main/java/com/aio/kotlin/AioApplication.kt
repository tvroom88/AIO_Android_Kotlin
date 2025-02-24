package com.aio.kotlin

import android.app.Application
import android.content.Context
import com.aio.kotlin.utils.DataStoreUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AioApplication : Application() {

    private lateinit var dataStore: DataStoreUtil

    companion object {
        private lateinit var instance: AioApplication

        fun getInstance(): AioApplication = instance
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        dataStore = DataStoreUtil(this)
    }

    fun getDataStore() : DataStoreUtil = dataStore
}
