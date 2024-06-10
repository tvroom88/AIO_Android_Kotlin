package com.aio.kotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _mBinding: VB? = null
    val mBinding get() = _mBinding!!

    protected abstract fun getViewBinding(): VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mBinding = getViewBinding()
        setContentView(mBinding.root) // 2
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}