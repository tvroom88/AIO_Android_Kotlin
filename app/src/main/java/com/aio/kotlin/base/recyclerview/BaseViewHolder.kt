package com.aio.kotlin.base.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * DataBinding 적용.
 */
abstract class BaseViewHolder<out B : ViewDataBinding, D>(
    parent: ViewGroup,
    @LayoutRes layoutRes: Int
) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {

    val context: Context = itemView.context
    val binding: B = DataBindingUtil.bind(itemView)!!

    abstract fun bind(data: D)
    open fun recycled() {}
}