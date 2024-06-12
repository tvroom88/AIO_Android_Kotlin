package com.aio.kotlin.activities

import android.os.Bundle
import com.aio.kotlin.R
import com.aio.kotlin.base.ViewBindingBaseActivity
import com.aio.kotlin.databinding.ActivityDetailBinding
import com.aio.kotlin.jetpack.binding.databinding.DataBindingFragment

class DetailActivity : ViewBindingBaseActivity<ActivityDetailBinding>() {
    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Fragment 추가
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_detail, DataBindingFragment())
                .commit()
        }
    }
}