package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.aio.kotlin.R
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.base.recyclerview.BaseViewHolder
import com.aio.kotlin.databinding.ItemCoroutineTestBinding
import com.aio.kotlin.databinding.ItemPokemonBinding
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.remote.Pokemon
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.bumptech.glide.Glide

class PokemonAdapter :
    BaseRecyclerViewAdapter<PokemonAdapter.StudyListViewHolder, Pokemon>() {

    override fun getViewHolder(parent: ViewGroup) = StudyListViewHolder(parent)

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(mutableList: MutableList<Pokemon>?) {
        items = mutableList ?: mutableListOf()
        notifyDataSetChanged()
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

//            // Glide를 사용해 이미지 로드
//            Glide.with(binding.root.context)
//                .load(data.getImageUrl())              // Pokemon의 이미지 URL
//                .into(binding.ivMvvmAdvancedPokemon)    // ImageView에 로드
        }

        override fun recycled() {}
    }
}