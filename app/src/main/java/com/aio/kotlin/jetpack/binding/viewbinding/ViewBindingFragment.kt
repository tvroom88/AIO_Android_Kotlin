package com.aio.kotlin.jetpack.binding.viewbinding

import com.aio.kotlin.base.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentViewBindingBinding

class ViewBindingFragment : ViewBindingBaseFragment<FragmentViewBindingBinding>() {
    override fun getViewBinding(): FragmentViewBindingBinding {
       return FragmentViewBindingBinding.inflate(layoutInflater)
    }

    override fun initContentInOnCreateView() {

    }
}