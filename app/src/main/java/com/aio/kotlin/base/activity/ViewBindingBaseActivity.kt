package com.aio.kotlin.base.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import java.util.Objects

abstract class ViewBindingBaseActivity<T : ViewBinding> : AppCompatActivity() {

    private var _binding: T? = null
    protected val binding get() = _binding!!
    protected abstract fun getViewBinding(): T

    protected abstract fun initOnCreate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)

        initOnCreate() // 상속한 class에서 onCreate에 초기화
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected fun setToolbar(
        toolbar: Toolbar?,
        imageView: ImageView?,
        titleView: TextView?,
        title: String?
    ) {
        setSupportActionBar(toolbar) // Toolbar 세팅
        Objects.requireNonNull(supportActionBar)?.setDisplayShowTitleEnabled(false)
        imageView?.setOnClickListener { onBackPressed() }
        titleView?.text = title  // Title 제목 세팅
    }
}