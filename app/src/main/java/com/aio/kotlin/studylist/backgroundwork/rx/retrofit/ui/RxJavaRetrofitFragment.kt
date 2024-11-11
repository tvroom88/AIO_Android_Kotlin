package com.aio.kotlin.studylist.backgroundwork.rx.retrofit.ui

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.databinding.FragmentRxJavaRetrofitBinding
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.dto.RxRetrofitTestDTO
import com.aio.kotlin.studylist.recyclerview.ExampleItemDecoration
import dagger.hilt.android.AndroidEntryPoint

/**
 * 개방되어있는 테스트용 Api와 연결
 * https://jsonplaceholder.typicode.com/posts
 */
@AndroidEntryPoint
class RxJavaRetrofitFragment :
    DataBindingBaseFragment<FragmentRxJavaRetrofitBinding>(R.layout.fragment_rx_java_retrofit) {

    private lateinit var rxRetrofitTestViewModel: RxRetrofitTestViewModel
    private val rxRetrofitTestAdapter by lazy { RxRetrofitTestAdapter() }

    override fun initContentInOnViewCreated() {
        rxRetrofitTestViewModel = ViewModelProvider(this)[RxRetrofitTestViewModel::class.java]

        rxRetrofitTestViewModel.rxRetrofitTestData.observe(this) { rxRetrofitTestState ->

            when (rxRetrofitTestState.status) {
                Status.SUCCESS -> {
                    showLoadedData(rxRetrofitTestState.data)
                }

                Status.LOADING -> {
                    showLoadingView()
                }

                Status.ERROR -> {
                    showErrorMsg(rxRetrofitTestState.message)
                }
            }

        }

        binding?.apply {
            rxRetrofitTestVM = rxRetrofitTestViewModel

            btnRxRetrofitLoad.setOnClickListener {
                rxRetrofitTestViewModel.fetchAllData()
            }
        }

        binding?.rvRxRetrofitTest?.run {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = rxRetrofitTestAdapter.apply {
                onItemClickListener =
                    object : BaseRecyclerViewAdapter.OnItemClickListener<RxRetrofitTestDTO> {
                        override fun onItemClick(
                            binding: ViewDataBinding,
                            data: RxRetrofitTestDTO,
                            itemPosition: Int
                        ) {

                        }
                    }

                setItemList(null) // RecyclerView에 데이터 추가
            }
            addItemDecoration(ExampleItemDecoration(30, 60, 60))
        }
    }

    private fun showLoadedData(data: List<RxRetrofitTestDTO>?) {
        hideLoadingView()
        binding?.apply {
            data?.let {
//                tvRxRetrofitDataContent.text = it[0].title
                rxRetrofitTestAdapter.setItemList(data.toMutableList())
            }
        }
    }


    private fun showLoadingView() {
        binding?.apply {
            if (!pbRxRetrofitLoading.isVisible) {
                pbRxRetrofitLoading.visibility = View.VISIBLE
            }
        }
    }

    private fun hideLoadingView() {
        binding?.apply {
            if (pbRxRetrofitLoading.isVisible) {
                pbRxRetrofitLoading.visibility = View.GONE
            }
        }
    }

    private fun showErrorMsg(errMsg: String?) {
        hideLoadingView()
        Toast.makeText(activityContext, "에러 : $errMsg", Toast.LENGTH_SHORT).show()
        if (errMsg != null) {
            Log.d("errorerror", errMsg)
        }
    }
}