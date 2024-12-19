package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemPokemonBinding
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.model.Pokemon
import com.bumptech.glide.Glide

class PokemonAdapter :
    BaseRecyclerViewAdapter<PokemonAdapter.StudyListViewHolder, Pokemon>() {

    override fun getViewHolder(parent: ViewGroup) = StudyListViewHolder(parent)

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(newItems: MutableList<Pokemon>?) {

        if (!newItems.isNullOrEmpty()) {
            items = newItems
            Log.d("items", "items size after adding: ${items.size}")
            notifyDataSetChanged() // 모든 데이터 변경 알림
        }
    }

    /**
     * 여기에는 뷰 관련 내용만 세팅하고 실제 일의 수행은 Fragment나 Activity에서 onItemClick내에서 진행된다.
     */
    inner class StudyListViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemPokemonBinding, Pokemon>(
            parent,
            R.layout.item_pokemon
        ) {
        @SuppressLint("UseCompatLoadingForDrawables")
        override fun bind(data: Pokemon) {
            binding.pokemon = data // databinding으로 데이터 세팅. xml에서 title 세팅 예정
            Log.d("items", "data : ${data.name} ")

            Glide.with(binding.root.context)
                .load(data.getImageUrl())              // Pokemon의 이미지 URL
                .into(binding.ivMvvmAdvancedPokemon)    // ImageView에 로드
        }

        override fun recycled() {}
    }
}