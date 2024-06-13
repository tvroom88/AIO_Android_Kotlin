package com.aio.kotlin.base.recyclerview

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<VH : BaseViewHolder<ViewDataBinding, E>, E> :
    RecyclerView.Adapter<VH>() {

    protected val items = mutableListOf<E>()

    abstract fun getViewHolder(parent: ViewGroup): VH
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = items.size
    
    open fun getItem(position: Int): E =
        items[getItemPosition(position)]

    private fun getItemPosition(adapterPosition: Int) = adapterPosition

    override fun onViewRecycled(holder: VH) {
        holder.recycled()
        super.onViewRecycled(holder)
    }
}
