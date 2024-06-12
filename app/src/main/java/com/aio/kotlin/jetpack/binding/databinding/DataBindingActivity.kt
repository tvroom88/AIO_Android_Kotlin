package com.aio.kotlin.jetpack.binding.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataBindingBinding
    private lateinit var dbViewModel: DataBindingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding 설정
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)

        // Bind lifecycle owner for LiveData observation
        binding.lifecycleOwner = this

        // ViewModel 초기화
        dbViewModel = ViewModelProvider(this)[DataBindingViewModel::class.java]

        binding.viewModel = dbViewModel

    }
}
