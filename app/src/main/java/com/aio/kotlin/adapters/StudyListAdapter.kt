package com.aio.kotlin.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemStudyListBinding
import com.aio.kotlin.models.StudyList


class StudyListAdapter :
    BaseRecyclerViewAdapter<StudyListAdapter.StudyListViewHolder, StudyList>() {

    var studyListAdapter = this

    override fun getViewHolder(parent: ViewGroup) = StudyListViewHolder(parent)

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(mutableList: MutableList<StudyList>) {
        items = mutableList
        notifyDataSetChanged()
    }

    /**
     * 여기에는 뷰 관련 내용만 세팅하고 실제 일의 수행은 Fragment나 Activity에서 onItemClick내에서 진행된다.
     */
    inner class StudyListViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemStudyListBinding, StudyList>(parent, R.layout.item_study_list) {
        @SuppressLint("UseCompatLoadingForDrawables")
        override fun bind(studyList: StudyList) {
            binding.studylist = studyList // databinding으로 데이터 세팅. xml에서 title 세팅 예정

            //Expandable 라이브러리의 parent 부분
            val expandableLayoutParent = binding.expandable.parentLayout
            expandableLayoutParent.findViewById<TextView>(R.id.tv_parent_study_list).text =
                studyList.title

            //Expandable 라이브러리의 child 부분
            val expandableLayoutChild = binding.expandable.secondLayout
            val sublistRV =
                expandableLayoutChild.findViewById<RecyclerView>(R.id.rv_second_study_list)
            
            // Child 부분에도 안드로이드 학습 목록의 sublist를 보여주기 위해 recyclerview 적용
            sublistRV.run {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                val studySubListAdapter = StudySubListAdapter() // 매번 생성해준다.
                adapter = studySubListAdapter.apply {
                    onItemClickListener = studyListAdapter.onItemClickListener // 현재의 똑같은 onClickListener 사용 예정
                    if (studyList is StudyList.StudyCategory) {
                        setItemList(studyList.studyList)
                    }
                }

                addItemDecoration(StudySubListItemDecoration(0, 0, 0))

                if(studyList !is StudyList.StudyCategory){
                    binding.expandable.spinnerDrawable = resources.getDrawable(R.drawable.arrow_right_white, context.theme);
                }
            }
        }

        override fun recycled() {}
    }
}

