package com.aio.kotlin.studylist.backgroundwork.rx.baseclasses

import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentRxJavaBaseClassesBinding


class RxJavaBaseClassesFragment :
    DataBindingBaseFragment<FragmentRxJavaBaseClassesBinding>(R.layout.fragment_rx_java_base_classes) {
    private var rxJavaViewModel: RxJavaBaseClassesViewModel? = null

    override fun initContentInOnViewCreated() {
        rxJavaViewModel = ViewModelProvider(this)[RxJavaBaseClassesViewModel::class.java]

        binding?.apply {
            rxJavaBaseViewModel = rxJavaViewModel

            // 첫번째 버튼 클릭시 타이머 시작 및 status 알림 변경
            btnRxjavabaseStart.setOnClickListener {
                rxJavaViewModel?.rxJavaObservableClass()
            }

            // 첫번째 버튼 클릭시 타이머 시작 및 status 알림 변경
            btnRxjavabaseStart2.setOnClickListener {
                rxJavaViewModel?.rxJavaSingleClass()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        rxJavaViewModel?.disposableDispose()
    }

}