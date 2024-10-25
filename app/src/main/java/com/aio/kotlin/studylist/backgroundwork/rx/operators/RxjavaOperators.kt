package com.aio.kotlin.studylist.backgroundwork.rx.operators

import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentRxjavaOperatorsBinding

/**
 * Rxjava 연산자들
 */
class RxjavaOperators :
    DataBindingBaseFragment<FragmentRxjavaOperatorsBinding>(R.layout.fragment_rxjava_operators) {
    private lateinit var rxjavaOperatorsViewModel: RxJavaOperatorsViewModel

    override fun initContentInOnViewCreated() {
        rxjavaOperatorsViewModel = ViewModelProvider(this)[RxJavaOperatorsViewModel::class.java]

        binding?.apply {
            myRxJavaOperatorsViewModel = rxjavaOperatorsViewModel

            // 생성 연산자 : just(), create(), interval()
            btnRxjavaoperatorsJust.setOnClickListener {
                rxjavaOperatorsViewModel.operatorJust()
            }

            btnRxjavaoperatorsCreate.setOnClickListener {
                rxjavaOperatorsViewModel.operatorCreate()
            }

            btnRxjavaoperatorsInterval.setOnClickListener {
                rxjavaOperatorsViewModel.operatorInterval()
            }

            // Transforming Observables : map()
            btnRxjavaoperatorsMap.setOnClickListener {
                rxjavaOperatorsViewModel.operatorMap()
            }

            // Filtering Observables : Debounce, Throttle
            btnRxjavaoperatorsDebounce.setOnClickListener {
                rxjavaOperatorsViewModel.operatorDebounce()
            }

            btnRxjavaoperatorsThrottle.setOnClickListener {
                rxjavaOperatorsViewModel.operatorThrottle()
            }


        }

    }
}