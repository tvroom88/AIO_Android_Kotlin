package com.aio.kotlin.studylist.backgroundwork.rx.retrofit.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemRxRetrofitTestBinding
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.dto.RxRetrofitTestDTO

class RxRetrofitTestAdapter : BaseRecyclerViewAdapter<RxRetrofitTestAdapter.StudyListViewHolder, RxRetrofitTestDTO>() {

    override fun getViewHolder(parent: ViewGroup) = StudyListViewHolder(parent)

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(mutableList: MutableList<RxRetrofitTestDTO>?) {
        if(mutableList != null){
            items = mutableList
        }
        notifyDataSetChanged()
    }

    /**
     * 여기에는 뷰 관련 내용만 세팅하고 실제 일의 수행은 Fragment나 Activity에서 onItemClick내에서 진행된다.
     */
    inner class StudyListViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemRxRetrofitTestBinding, RxRetrofitTestDTO>(parent, R.layout.item_rx_retrofit_test) {
        @SuppressLint("UseCompatLoadingForDrawables")
        override fun bind(rxRetrofitTestData: RxRetrofitTestDTO) {
            binding.rxRetrofitTestDTO = rxRetrofitTestData // databinding으로 데이터 세팅. xml에서 title 세팅 예정
        }

        override fun recycled() {}
    }
}