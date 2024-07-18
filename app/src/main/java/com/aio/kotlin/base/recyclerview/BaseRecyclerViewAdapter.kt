package com.aio.kotlin.base.recyclerview

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<VH : BaseViewHolder<ViewDataBinding, E>, E> :
    RecyclerView.Adapter<VH>() {

    // RecyclerView에 들어가는 item
    protected var items = mutableListOf<E>()

    // 클릭 이벤트 리스너
    var onItemClickListener: OnItemClickListener<E>? = null

    interface OnItemClickListener<E> {
        fun onItemClick(data: E, itemPosition: Int)
    }

    abstract fun getViewHolder(parent: ViewGroup): VH
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        Log.d("BaseRecyclerViewAdapter", "onCreateViewHolder")
        return getViewHolder(parent).apply {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(
                    getItem(adapterPosition),
                    adapterPosition
                )
            }
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Log.d("BaseRecyclerViewAdapter", "onBindViewHolder")
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        Log.d("BaseRecyclerViewAdapter", "getItemCount ${items.size}")
        return items.size
    }

    open fun getItem(position: Int): E = items[position]


    override fun onViewRecycled(holder: VH) {
        holder.recycled()
        super.onViewRecycled(holder)
    }
}
