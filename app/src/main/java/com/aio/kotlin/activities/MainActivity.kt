package com.aio.kotlin.activities

import android.content.Intent
import android.widget.Toast
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.databinding.ActivityMainBinding


class MainActivity : ViewBindingBaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initOnCreate() {

        // toolbar 설정
        setToolbar(
            binding.layout.toolbar,
            binding.layout.toolbarImage,
            binding.layout.tooblarTitle,
            "안드로이드 학습 앱"
        );


        binding.btnBasicStudyMain.setOnClickListener {
            val intent = Intent(this@MainActivity, AndroidStudyActivity::class.java)
            startActivity(intent)
        }

        binding.btnAdvancedStudyMain.setOnClickListener{
            Toast.makeText(this, "준비중입니다.", Toast.LENGTH_SHORT);
        }
    }
}