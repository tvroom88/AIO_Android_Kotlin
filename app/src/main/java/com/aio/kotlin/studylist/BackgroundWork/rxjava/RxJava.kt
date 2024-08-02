package com.aio.kotlin.studylist.backgroundwork.rxjava

import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentRxjavaBinding

class RxJava : DataBindingBaseFragment<FragmentRxjavaBinding>(R.layout.fragment_rxjava) {
    private lateinit var rxJavaViewModel: RxJavaViewModel
    override fun initContentInOnViewCreated() {
        rxJavaViewModel = ViewModelProvider(this)[RxJavaViewModel::class.java]
//        binding.viewModel = dbViewModel
    }

}
