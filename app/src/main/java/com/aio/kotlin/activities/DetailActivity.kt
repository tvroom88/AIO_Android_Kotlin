package com.aio.kotlin.activities

import android.os.Bundle
import com.aio.kotlin.R
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.databinding.ActivityDetailBinding
import com.aio.kotlin.jetpack.binding.databinding.DataBindingExampleFragment
import com.aio.kotlin.recyclerview.RecyclerViewExampleFragment

class DetailActivity : ViewBindingBaseActivity<ActivityDetailBinding>() {
    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_detail, RecyclerViewExampleFragment())
                .commit()
        }
    }
}