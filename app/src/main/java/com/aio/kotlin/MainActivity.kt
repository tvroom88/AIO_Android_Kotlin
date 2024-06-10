package com.aio.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aio.kotlin.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}