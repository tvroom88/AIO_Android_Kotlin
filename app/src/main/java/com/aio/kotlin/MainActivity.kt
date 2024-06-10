package com.aio.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aio.kotlin.base.BaseActivity
import com.aio.kotlin.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    // ViewBinding 연결
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}