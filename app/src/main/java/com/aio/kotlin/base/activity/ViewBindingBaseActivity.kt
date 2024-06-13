package com.aio.kotlin.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class ViewBindingBaseActivity<T : ViewBinding> : AppCompatActivity() {

    private var _binding: T? = null
    protected val binding get() = _binding!!
    protected abstract fun getViewBinding(): T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        _binding = getViewBinding()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}