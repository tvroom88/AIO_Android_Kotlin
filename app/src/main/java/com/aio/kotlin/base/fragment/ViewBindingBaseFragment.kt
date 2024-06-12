package com.aio.kotlin.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class ViewBindingBaseFragment<VB : ViewBinding> : BaseFragment<VB>() {

    private var _mBinding: VB? = null
    protected val mBinding get() = _mBinding!!

    protected abstract fun getViewBinding(): VB
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mBinding = getViewBinding()

        initContentInOnCreateView()
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}
