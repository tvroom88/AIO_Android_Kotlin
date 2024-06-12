package com.aio.kotlin.jetpack.binding.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.base.DataBindingBaseActivity
import com.aio.kotlin.databinding.ActivityDataBindingBinding

class DataBindingActivity : DataBindingBaseActivity<ActivityDataBindingBinding>(R.layout.activity_data_binding) {

    private lateinit var dbViewModel: DataBindingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewModel 초기화
        dbViewModel = ViewModelProvider(this)[DataBindingViewModel::class.java]

        mBinding.viewModel = dbViewModel

    }
}
