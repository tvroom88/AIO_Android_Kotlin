package com.aio.kotlin.studylist.jetpack.roomdb.view

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemRecyclerviewExampleBinding
import com.aio.kotlin.databinding.ItemRoomdbBinding
import com.aio.kotlin.studylist.jetpack.roomdb.RoomDbUser

class RoomDbAdapter : BaseRecyclerViewAdapter<RoomDbAdapter.TestViewHolder, RoomDbUser>() {

    var onButtonClick: ((RoomDbUser) -> Unit)? = null // 버튼 클릭 이벤트를 처리하기 위한 람다

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(list: List<RoomDbUser>){
        items = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun getViewHolder(parent: ViewGroup) = TestViewHolder(parent)

    inner class TestViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemRoomdbBinding, RoomDbUser>(parent, R.layout.item_roomdb) {
        override fun bind(data: RoomDbUser) {
            binding.roomDbUser = data

            binding.btnRoomdbItemDelete.setOnClickListener {
                onButtonClick?.invoke(data)
            }
        }
        override fun recycled() {}
    }


}