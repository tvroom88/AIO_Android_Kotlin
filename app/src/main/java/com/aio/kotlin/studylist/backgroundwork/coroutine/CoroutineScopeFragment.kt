package com.aio.kotlin.studylist.backgroundwork.coroutine

import android.widget.TextView
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentCoroutineBuilderBinding
import com.aio.kotlin.databinding.FragmentCoroutineScopeBinding

/**
 * (1) MainThread 사용후 Worker THread
 * (2) WorkerThead 사용후 MainThread
 */
class CoroutineScopeFragment : ViewBindingBaseFragment<FragmentCoroutineScopeBinding>() {

    private lateinit var resultTextView: TextView

    override fun getViewBinding(): FragmentCoroutineScopeBinding {
        return FragmentCoroutineScopeBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {
        TODO("Not yet implemented")
    }

}