package com.aio.kotlin.base.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.aio.kotlin.R

abstract class DataBindingBaseFragment<T : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    // --- context, activity ---
    private var _context: Context? = null
    protected val activityContext get() = _context!!

    private var _activity: Activity? = null
    protected val activity get() = _activity!!

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
        _binding =
            DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initContentInOnViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getFullFragmentName(): String {
        // 현재 프래그먼트의 클래스 객체를 가져옵니다
        val fragmentClass = this::class.java

        // 패키지 이름과 클래스 이름을 가져와서 결합합니다
        val packageName = fragmentClass.`package`?.name ?: ""
        val className = fragmentClass.simpleName

        return "$packageName.$className"
    }
}
