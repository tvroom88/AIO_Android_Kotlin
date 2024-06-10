package com.aio.kotlin.jetpack.binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aio.kotlin.R
import com.aio.kotlin.base.BaseActivity
import com.aio.kotlin.databinding.ActivityMainBinding
import com.aio.kotlin.databinding.ActivityViewBindingBinding

class ViewBindingActivity : BaseActivity<ActivityViewBindingBinding>() {

    // getViewBinding 메서드를 구현하여 ViewBinding 인스턴스를 반환
    override fun getViewBinding(): ActivityViewBindingBinding {
        return ActivityViewBindingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}