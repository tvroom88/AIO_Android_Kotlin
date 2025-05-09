package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui.fragment

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemRecyclerviewGithubBinding
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.entity.remote.RemoteGithubModel
import com.bumptech.glide.Glide

class GitHubAdapter : BaseRecyclerViewAdapter<GitHubAdapter.GithubViewHolder, RemoteGithubModel>() {

    fun addItem(item: RemoteGithubModel) {
        items.add(item) // 데이터에 추가
        notifyItemInserted(items.size - 1) // 새 항목을 삽입
    }

    fun addItems(newItems: List<RemoteGithubModel>) {
        val startPosition = items.size
        items.addAll(newItems) // 데이터에 여러 항목 추가
        notifyItemRangeInserted(startPosition, newItems.size) // 추가된 항목을 알림
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(mutableList: MutableList<RemoteGithubModel>) {
        items = mutableList
        notifyDataSetChanged()
    }

    override fun getViewHolder(parent: ViewGroup) = GithubViewHolder(parent)

    inner class GithubViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemRecyclerviewGithubBinding, RemoteGithubModel>(
            parent,
            R.layout.item_recyclerview_github
        ) {
        override fun bind(data: RemoteGithubModel) {
            binding.url = data.avatarUrl
            Glide.with(binding.root.context)
                .load(data.avatarUrl)              // Pokemon의 이미지 URL
                .into(binding.ivAvatar)    // ImageView에 로드
        }

        override fun recycled() {}
    }

}