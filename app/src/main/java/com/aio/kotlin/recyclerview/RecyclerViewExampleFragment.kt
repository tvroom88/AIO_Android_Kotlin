package com.aio.kotlin.recyclerview

import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentRecyclerViewExampleBinding

class RecyclerViewExampleFragment : ViewBindingBaseFragment<FragmentRecyclerViewExampleBinding>() {

//    private val testAdapter by lazy { TestAdapter() }
    override fun getViewBinding(): FragmentRecyclerViewExampleBinding {
        return FragmentRecyclerViewExampleBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {
        TODO("Not yet implemented")
    }


}
