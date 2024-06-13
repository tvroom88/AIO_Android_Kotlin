package com.aio.kotlin.base.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class ViewBindingBaseFragment<T : ViewBinding> : Fragment() {

    private var _mBinding: T? = null
    protected val mBinding get() = _mBinding!!

    // --- context, activity ---
    private var _context: Context? = null
    protected val context get() = _context!!

    private var _activity: Activity? = null
    protected val activity get() = _activity!!

    protected abstract fun getViewBinding(): T
    protected abstract fun initContentInOnViewCreated()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context = context
        _activity = context as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mBinding = getViewBinding()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContentInOnViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}
