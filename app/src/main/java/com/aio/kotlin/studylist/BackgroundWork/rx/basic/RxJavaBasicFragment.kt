package com.aio.kotlin.studylist.backgroundwork.rx.basic

import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentRxjavaBinding

class RxJavaBasicFragment : DataBindingBaseFragment<FragmentRxjavaBinding>(R.layout.fragment_rxjava) {
    private lateinit var rxJavaViewModel: RxJavaBasicViewModel
    override fun initContentInOnViewCreated() {
        rxJavaViewModel = ViewModelProvider(this)[RxJavaBasicViewModel::class.java]
        binding.viewModel = rxJavaViewModel

        /**
         * Class : Observable
         * Operator : zip(), just(), interval()
         */
        rxJavaViewModel.rxJavaExample1() // 1~5초 count - 1

        /**
         * Observable : Observable
         * Observer : Observer
         * Operator : zip(), just(), interval()
         * Scheduler : Schedulers.computation(), Schedulers.io()
         */
        rxJavaViewModel.rxJavaExample2() // 1~5초 count - 2

        /**
         * Observable : Observable
         * Observer : DisposableObserver
         * Scheduler : Schedulers.computation(), Schedulers.io()
         */
        rxJavaViewModel.rxJavaExample3() //  1~5초 count - 3
    }
}
