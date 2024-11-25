package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.home

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.databinding.FragmentCoroutineTestBinding
import com.aio.kotlin.databinding.FragmentRxJavaRetrofitBinding
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.adapter.CoroutineTestAdapter
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.dto.RxRetrofitTestDTO
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.ui.RxRetrofitTestAdapter
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.ui.Status
import com.aio.kotlin.studylist.recyclerview.ExampleItemDecoration
import dagger.hilt.android.AndroidEntryPoint

/**
 * 코루틴에 Clean Architecture를 접목한 예제
 *
 * local : RoomDataBase
 * remote : Retrofit
 *
 *
 * remote: 생성 과정
 * (1) CoroutineTestDto (data class)
 * (2) CoroutineTestApi (EndPoint interface)
 * (3) di (CoroutineTestAPi & Retrofit)
 * (4) RemoteDataSource 생성 (여기서 CoroutineTestApi로 데이터 받는 부분 생성)
 * (5) di (이것도 di로 생성)
 *
 * Repository 생성
 */

@AndroidEntryPoint
class CoroutineTestFragment :
    DataBindingBaseFragment<FragmentCoroutineTestBinding>(R.layout.fragment_coroutine_test) {

    private val coroutineTestViewModel by viewModels<CoroutineTestViewModel>()
    private val coroutineTestAdapter by lazy { CoroutineTestAdapter() }

    override fun initContentInOnViewCreated() {
        binding?.apply {
            coroutineTestVM = coroutineTestViewModel

            btnCoroutineTestStart.setOnClickListener {
                coroutineTestViewModel.getCoroutineTestData()
            }

            rvCoroutineTest.run {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = coroutineTestAdapter.apply {
                    setItemList(null) // RecyclerView에 데이터 추가
                }
                addItemDecoration(ExampleItemDecoration(30, 60, 60))
            }
        }

        coroutineTestViewModel.apply {
            coroutineTestData.observe(viewLifecycleOwner) {
                showLoadedData(it)
            }

            errorMsg.observe(viewLifecycleOwner) {
                Toast.makeText(activityContext, it, Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun showLoadedData(data: List<CoroutineTest>?) {
        hideLoadingView()
        binding?.apply {
            data?.let {
                coroutineTestAdapter.setItemList(data.toMutableList())
            }
        }
    }


    private fun showLoadingView() {
        binding?.apply {
            if (!pbCoroutineTestLoading.isVisible) {
                pbCoroutineTestLoading.visibility = View.VISIBLE
            }
        }
    }

    private fun hideLoadingView() {
        binding?.apply {
            if (pbCoroutineTestLoading.isVisible) {
                pbCoroutineTestLoading.visibility = View.GONE
            }
        }
    }

}