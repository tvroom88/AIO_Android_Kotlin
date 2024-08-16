package com.aio.kotlin.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemStudySublistBinding
import com.aio.kotlin.models.StudyList

class StudySubListAdapter :
    BaseRecyclerViewAdapter<StudySubListAdapter.StudyListViewHolder, StudyList>() {


    override fun getViewHolder(parent: ViewGroup) = StudyListViewHolder(parent)

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(mutableList: MutableList<StudyList>) {
        items = mutableList
        notifyDataSetChanged()
    }


    inner class StudyListViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemStudySublistBinding, StudyList>(parent, R.layout.item_study_sublist) {
        override fun bind(studyList: StudyList) {
            binding.subStudyList = studyList // databinding으로 데이터 세팅. xml에서 title 세팅 예정
        }

        override fun recycled() {}
    }
}
