package com.aio.kotlin.studylist.backgroundwork.coroutine

import android.widget.TextView
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentCoroutineBuilderBinding
import com.aio.kotlin.databinding.FragmentCoroutineScopeBinding

class CoroutineScopeFragment : ViewBindingBaseFragment<FragmentCoroutineScopeBinding>() {

    private lateinit var resultTextView: TextView

    override fun getViewBinding(): FragmentCoroutineScopeBinding {
        return FragmentCoroutineScopeBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {
        TODO("Not yet implemented")
    }

}