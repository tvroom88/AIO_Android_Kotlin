package com.aio.kotlin.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemStudyListBinding
import com.aio.kotlin.models.StudyList


class StudyListAdapter :
    BaseRecyclerViewAdapter<StudyListAdapter.StudyListViewHolder, StudyList>() {
    override fun getViewHolder(parent: ViewGroup) = StudyListViewHolder(parent)

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(mutableList: MutableList<StudyList>) {
        items = mutableList
        notifyDataSetChanged()
    }

    fun updateItem(position: Int, updatedData: StudyList) {
        items[position] = updatedData
        notifyItemChanged(position)
    }

    inner class StudyListViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemStudyListBinding, StudyList>(parent, R.layout.item_study_list) {
        override fun bind(studyList: StudyList) {
            binding.studylist = studyList

//            if (studyList.categoryVisibility(studyList) == View.VISIBLE) {
//                hiddenView.animate()
//                    .translationY(hiddenView.height.toFloat()) // 화면 아래로 이동
//                    .alpha(1.0f) // 투명하게 만듦
//                    .setDuration(2000)
//                    .setListener(object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator) {
//                            super.onAnimationEnd(animation)
//                            hiddenView.visibility = View.GONE // 애니메이션 끝난 후 GONE 상태로 설정
//                            hiddenView.translationY = 0f // 위치 초기화
//                        }
//                    })
//            } else {
//                hiddenView.translationY = hiddenView.height.toFloat() // 초기 위치를 화면 아래로 설정
//                hiddenView.visibility = View.VISIBLE // 뷰를 VISIBLE로 설정
//
//                hiddenView.animate()
//                    .translationY(0f) // 원래 위치로 이동
//                    .alpha(0.0f) // 투명도 1로 설정
//                    .setDuration(2000)
//                    .setListener(null) // 별도 리스너 필요 없음
//            }


        }

        override fun recycled() {}
    }
}

