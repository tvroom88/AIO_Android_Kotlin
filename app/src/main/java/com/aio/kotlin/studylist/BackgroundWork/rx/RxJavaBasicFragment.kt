package com.aio.kotlin.studylist.backgroundwork.rx

import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentRxjavaBinding

class RxJavaBasicFragment : DataBindingBaseFragment<FragmentRxjavaBinding>(R.layout.fragment_rxjava) {
    private lateinit var rxJavaViewModel: RxJavaViewModel
    override fun initContentInOnViewCreated() {
        rxJavaViewModel = ViewModelProvider(this)[RxJavaViewModel::class.java]
        binding.viewModel = rxJavaViewModel

        // 1~5초 count - 1
        rxJavaViewModel.rxJavaExample1()

        // 1~5초 count - 2
        rxJavaViewModel.rxJavaExample2()
    }
}
