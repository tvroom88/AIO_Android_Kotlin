package com.aio.kotlin.recyclerview

import android.view.ViewGroup
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemRecyclerviewExampleBinding

class TestAdapter : BaseRecyclerViewAdapter<TestAdapter.TestViewHolder, TestModel>() {


    override fun getViewHolder(parent: ViewGroup) = TestViewHolder(parent)

    inner class TestViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemRecyclerviewExampleBinding, TestModel>(parent, R.layout.item_recyclerview_example) {
        override fun bind(data: TestModel) {}
        override fun recycled() {}
    }

}