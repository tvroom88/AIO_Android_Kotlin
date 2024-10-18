package com.aio.kotlin.utils

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.View

/**
 * Worker Thread에서 UI를 변경하는 방법 모음.
 */
class HandlerUtil {

    private val handler = Handler(Looper.getMainLooper())

    // 1. Handler 사용
    fun handlerPost(function: () -> Unit){
        handler.post {
            function()// UI 업데이트
        }
    }

    // 2. runOnUiThread 사용
    fun runOnUiThread(activity: Activity, function: () -> Unit){
        activity.runOnUiThread {
            function() // UI 업데이트
        }
    }

    // 3. View.post 사용
    fun viewPost(view: View, function: () -> Unit){
        view.post{
            function() // UI 업데이트
        }
    }
}