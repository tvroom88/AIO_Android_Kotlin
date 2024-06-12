package com.aio.kotlin.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * View 클릭시 Activity를 이동시키는 기능이다.
     *
     * @param view  onClick할 View의 대상
     * @param activity 이동할 activity
     */
    fun moveActivityWithOnClick(view: View, activity: Activity) {
        view.setOnClickListener {
            val intent = Intent(this, activity::class.java)
            startActivity(intent)
        }
    }
}
