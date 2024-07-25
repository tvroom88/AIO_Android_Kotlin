package com.aio.kotlin.studylist.jetpack.binding.databinding

import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentDataBindingBinding

class DataBindingExampleFragment : DataBindingBaseFragment<FragmentDataBindingBinding>(R.layout.fragment_data_binding) {

    private lateinit var dbViewModel: DataBindingViewModel
    override fun initContentInOnViewCreated() {
        dbViewModel = ViewModelProvider(this)[DataBindingViewModel::class.java]
        binding.viewModel = dbViewModel
    }
}