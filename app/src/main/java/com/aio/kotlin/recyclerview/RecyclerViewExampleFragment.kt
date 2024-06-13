package com.aio.kotlin.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentRecyclerViewExampleBinding

class RecyclerViewExampleFragment : ViewBindingBaseFragment<FragmentRecyclerViewExampleBinding>() {

    private val testAdapter by lazy { TestAdapter() }
    override fun getViewBinding(): FragmentRecyclerViewExampleBinding {
        return FragmentRecyclerViewExampleBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {
        testAdapter.setItemList(setTestItem())
        binding.rvExample.run {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = testAdapter
        }
    }

    private fun setTestItem(): MutableList<String> {
        val tempList = mutableListOf<String>()
        for (num in 0..1000) {
            tempList.add(num.toString())
        }
        return tempList
    }
}
