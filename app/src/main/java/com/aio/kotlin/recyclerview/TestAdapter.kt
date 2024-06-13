package com.aio.kotlin.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemRecyclerviewExampleBinding

class TestAdapter : BaseRecyclerViewAdapter<TestAdapter.TestViewHolder, String>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(mutableList: MutableList<String>){
        items = mutableList
        notifyDataSetChanged()
    }

    override fun getViewHolder(parent: ViewGroup) = TestViewHolder(parent)

    inner class TestViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemRecyclerviewExampleBinding, String>(parent, R.layout.item_recyclerview_example) {
        override fun bind(data: String) {
            binding.tvRecyclerExample.text = data
        }
        override fun recycled() {}
    }

}