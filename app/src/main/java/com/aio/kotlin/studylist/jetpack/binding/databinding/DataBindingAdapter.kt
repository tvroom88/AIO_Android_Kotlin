package com.aio.kotlin.studylist.jetpack.binding.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object DataBindingAdapter {

    // ImageView와 Glide 연결
    @JvmStatic
    @BindingAdapter("imageRes")
    fun loadImage(view: ImageView, imageRes: Int?) {
        imageRes?.let {
            Glide.with(view.context)
                .load(it)
                .into(view)
        }
    }
}