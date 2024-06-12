package com.aio.kotlin.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding

abstract class ViewBindingBaseActivity<VB : ViewBinding> : BaseActivity<VB>() {

    private var _mBinding: VB? = null
    protected val mBinding get() = _mBinding!!
    protected abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        _mBinding = getViewBinding()
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}