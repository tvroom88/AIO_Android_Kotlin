package com.aio.kotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aio.kotlin.R

abstract class DataBindingBaseFragment<VB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    BaseFragment<VB>() {

    private var _mBinding: VB? = null
    protected val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_data_binding, container, false)

        // Bind lifecycle owner for LiveData observation
        mBinding.lifecycleOwner = this

        initContentInOnCreateView()

        return mBinding.root
    }

}