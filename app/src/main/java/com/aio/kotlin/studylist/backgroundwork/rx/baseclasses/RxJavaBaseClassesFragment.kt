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

            // 첫번째 버튼 클릭시 Observerable로 구현된 타이머 시작 및 status 알림 변경
            btnRxjavabaseObservableStart.setOnClickListener {
                rxJavaViewModel?.rxJavaObservableClass()
            }

            // 두번째 버튼 클릭시 Single 구현된 status 알림 변경
            btnRxjavabaseSingleStart.setOnClickListener {
                rxJavaViewModel?.rxJavaSingleClass()
            }

            // 세번째 버튼 클릭시 Maybe 구현된 status 알림 변경
            btnRxjavabaseMaybeStart.setOnClickListener {
                rxJavaViewModel?.rxJavaMaybeClass()
            }

            // 네번쨰 버튼 클릭시 Completable 구현된 status 알림 변경
            btnRxjavabaseCompletableStart.setOnClickListener {
                rxJavaViewModel?.rxJavaCompletableClass()
            }

            // 다섯번째 버튼 클릭시 Flowable 구현된 타이머 시작 및 status 알림 변경
            btnRxjavabaseFlowableStart.setOnClickListener {
                rxJavaViewModel?.rxJavaFlowableClass()
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        rxJavaViewModel?.disposableDispose()
    }

}