package com.aio.kotlin.activities

import android.content.Intent
import android.os.Bundle
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.databinding.ActivityMainBinding

class MainActivity : ViewBindingBaseActivity<ActivityMainBinding>() {

    // ViewBinding 연결
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDetailActivityButton()
    }

    private fun setDetailActivityButton() {
        binding.button.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }
}