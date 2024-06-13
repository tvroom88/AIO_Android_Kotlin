package com.aio.kotlin.jetpack.binding.viewbinding

import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentViewBindingBinding

class ViewBindingExampleFragment : ViewBindingBaseFragment<FragmentViewBindingBinding>() {
    override fun getViewBinding(): FragmentViewBindingBinding =
        FragmentViewBindingBinding.inflate(layoutInflater)

    override fun initContentInOnViewCreated() {}
}