package com.aio.kotlin.recyclerview

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.databinding.FragmentRecyclerViewExampleBinding

class RecyclerViewExampleFragment : ViewBindingBaseFragment<FragmentRecyclerViewExampleBinding>() {

    private val testAdapter by lazy { ExampleAdapter() }
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
            adapter = testAdapter.apply {
                onItemClickListener = object : BaseRecyclerViewAdapter.OnItemClickListener<String> {
                    override fun onItemClick(data: String, itemPosition: Int) {
                        // 클린 이벤트에 필요한 내용
                        Log.d("RecyclerViewExampleFragment", "item$itemPosition, data : $data")
                    }
                }
            }
            addItemDecoration(ExampleItemDecoration(30, 60, 60))
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
