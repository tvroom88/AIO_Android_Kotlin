package com.aio.kotlin.utils

import android.content.Context
import android.util.Log

class CheckThreadUtils {
    companion object {
        private const val TAG = "ThreadExample"
    }

    fun getThreadList(context: Context){
        val threads = Thread.getAllStackTraces()
        Log.d(TAG, context.packageName)
        for ((thread, stackTrace) in threads) {
            if (thread.name.contains(context.packageName)) {
                Log.d(TAG, "Thread: ${thread.name}")
                for (element in stackTrace) {
                    Log.d(TAG, "\t$element")
                }
            }
        }
    }
}