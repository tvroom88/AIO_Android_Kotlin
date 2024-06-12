package com.aio.kotlin

import android.os.Bundle
import com.aio.kotlin.base.ViewBindingBaseActivity
import com.aio.kotlin.databinding.ActivityMainBinding

class MainActivity : ViewBindingBaseActivity<ActivityMainBinding>() {

    // ViewBinding 연결
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}