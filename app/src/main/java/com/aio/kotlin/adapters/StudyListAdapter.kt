package com.aio.kotlin.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemStudyListBinding
import com.aio.kotlin.models.StudyList

class StudyListAdapter :  BaseRecyclerViewAdapter<StudyListAdapter.StudyListViewHolder, StudyList>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(mutableList: MutableList<StudyList>){
        items = mutableList
    }

    override fun getViewHolder(parent: ViewGroup) = StudyListViewHolder(parent)

    inner class StudyListViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemStudyListBinding, StudyList>(parent, R.layout.item_study_list) {
        override fun bind(studyList: StudyList) {
            binding.tvRecyclerExample.text = studyList.title
        }
        override fun recycled() {}
    }
}
