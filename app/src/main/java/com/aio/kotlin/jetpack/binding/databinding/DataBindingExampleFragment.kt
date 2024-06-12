package com.aio.kotlin.jetpack.binding.databinding

import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.base.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentDataBindingBinding

class DataBindingExampleFragment : DataBindingBaseFragment<FragmentDataBindingBinding>(R.layout.fragment_data_binding) {

    private lateinit var dbViewModel: DataBindingViewModel
    override fun initContentInOnCreateView() {
        dbViewModel = ViewModelProvider(this)[DataBindingViewModel::class.java]
        mBinding.viewModel = dbViewModel
    }
}