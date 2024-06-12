package com.aio.kotlin.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class DataBindingBaseActivity<VB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    BaseActivity<VB>() {

    private var _mBinding: VB? = null
    protected val mBinding get() = _mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mBinding = DataBindingUtil.setContentView(this, layoutResId)

        // Bind lifecycle owner for LiveData observation
        mBinding.lifecycleOwner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}