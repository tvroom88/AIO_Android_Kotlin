package com.aio.kotlin.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    // --- context, activity ---
    lateinit var mContext: Context
    lateinit var mActivity: Activity

    protected abstract fun initContentInOnCreateView()

    // 처음 fragment가 생성되고 activity에 붙을때 activity와 관련된 것들을 다 가져온다.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as Activity
    }
}