package com.aio.kotlin.studylist.backgroundwork.rx.operators

import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentRxjavaOperatorsBinding
import com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.RxJavaBaseClassesViewModel

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

            // 생성 연산자 : creating observables
            btnRxjavaoperatorsJust.setOnClickListener{
                rxjavaOperatorsViewModel.operatorJust()
            }

            btnRxjavaoperatorsCreate.setOnClickListener {
                rxjavaOperatorsViewModel.operatorCreate()
            }
        }

    }
}