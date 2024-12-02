package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemCoroutineCommentBinding
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineComment

class CoroutineCommentAdapter :
    BaseRecyclerViewAdapter<CoroutineCommentAdapter.StudyListViewHolder, CoroutineComment>() {

    override fun getViewHolder(parent: ViewGroup) = StudyListViewHolder(parent)

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(mutableList: MutableList<CoroutineComment>?) {
        items = mutableList ?: mutableListOf()
        notifyDataSetChanged()
    }

    /**
     * 여기에는 뷰 관련 내용만 세팅하고 실제 일의 수행은 Fragment나 Activity에서 onItemClick내에서 진행된다.
     */
    inner class StudyListViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemCoroutineCommentBinding, CoroutineComment>(
            parent,
            R.layout.item_coroutine_comment
        ) {
        @SuppressLint("UseCompatLoadingForDrawables")
        override fun bind(data: CoroutineComment) {
            binding.coroutineComment = data // databinding으로 데이터 세팅. xml에서 title 세팅 예정
        }

        override fun recycled() {}
    }
}