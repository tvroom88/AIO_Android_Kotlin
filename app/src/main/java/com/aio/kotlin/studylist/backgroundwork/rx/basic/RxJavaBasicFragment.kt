package com.aio.kotlin.studylist.backgroundwork.rx.basic

import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentRxjavaBinding

class RxJavaBasicFragment :
    DataBindingBaseFragment<FragmentRxjavaBinding>(R.layout.fragment_rxjava) {
    private lateinit var rxJavaViewModel: RxJavaBasicViewModel
    override fun initContentInOnViewCreated() {
        rxJavaViewModel = ViewModelProvider(this)[RxJavaBasicViewModel::class.java]
        binding?.viewModel = rxJavaViewModel

        binding?.apply {
            btnSampleRxjava1.setOnClickListener {
                rxJavaViewModel.rxJavaExample1() // Hello, World
            }

            btnSampleRxjava2.setOnClickListener {
                rxJavaViewModel.rxJavaExample2() // 1~5초 count - 1
            }
        }
    }
}
