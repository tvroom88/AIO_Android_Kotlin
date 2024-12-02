package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.home

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentCoroutineTestBinding
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineComment
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.model.CoroutineTest
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.domain.teststate.CoroutineStatus
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.adapter.CoroutineCommentAdapter
import com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.adapter.CoroutineTestAdapter
import com.aio.kotlin.studylist.recyclerview.ExampleItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * 코루틴에 Clean Architecture를 접목한 예제
 *
 * 1. Coroutine + Retrofit + LiveData (O)
 * 2. Coroutine + RoomDataBase (X)
 * 3. Coroutine + Retrofit + Flow + StateFlow (X)
 *
 * remote : Retrofit
 * local : RoomDataBase
 *
 * remote: 생성 과정
 * (1) CoroutineTestDto (data class)
 * (2) CoroutineTestApi (EndPoint interface)
 * (3) di (CoroutineTestAPi & Retrofit)
 * (4) RemoteDataSource 생성 (여기서 CoroutineTestApi로 데이터 받는 부분 생성)
 * (5) di (이것도 di로 생성)
 *
 * DI 방법:
 * 거의 모든 부분을 @Provides로 했지만
 * CoroutineTestRepositoryModulo 부분만 @Binds로 했다.
 * DI 사용방법을 익히기 위해서다.
 */

@AndroidEntryPoint
class CoroutineTestFragment :
    DataBindingBaseFragment<FragmentCoroutineTestBinding>(R.layout.fragment_coroutine_test) {

    private val coroutineTestViewModel by viewModels<CoroutineTestViewModel>()
    private val coroutineTestAdapter by lazy { CoroutineTestAdapter() }
    private val coroutineCommentAdapter by lazy { CoroutineCommentAdapter() }

    override fun initContentInOnViewCreated() {
        binding?.apply {
            coroutineTestVM = coroutineTestViewModel

            // Remote Data 가져오는 부분
            btnCoroutineTestLoadDataFromRemote.setOnClickListener {
                tvCoroutineArchitectureDataType.text = "Remote Data"
                coroutineTestViewModel.getCoroutineTestData()
            }

            btnCoroutineCommentLoadDataFromRemote.setOnClickListener {
                tvCoroutineArchitectureDataType.text = "Remote Data"
                coroutineTestViewModel.getCoroutineCommentData()
            }


            // Local Test Data 가져오는 부분
            btnCoroutineTestLoadDataFromLocal.setOnClickListener {
                tvCoroutineArchitectureDataType.text = "Local Comment Data"
                coroutineTestViewModel.getCoroutineTestLocalData()
            }

            btnCoroutineTestInsertDataToLocal.setOnClickListener {
                coroutineTestViewModel.insertCoroutineTestToLocal()
            }

            btnCoroutineTestDeleteAllDataFromLocal.setOnClickListener {
                coroutineTestViewModel.deleteAllCoroutineDataFromLocal()
            }

            // Local Comment Data 가져오는 부분
            btnCoroutineCommentLoadDataFromLocal.setOnClickListener {
                coroutineTestViewModel.getAllCommentData()
            }

            btnCoroutineTestInsertDataToLocal.setOnClickListener {
                coroutineTestViewModel.insertCoroutineCommentToLocal()
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

            rvCoroutineComment.run {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = coroutineCommentAdapter.apply {
                    setItemList(null) // RecyclerView에 데이터 추가
                }
                addItemDecoration(ExampleItemDecoration(30, 60, 60))
            }
        }

        initObserver()
    }

    private fun showLoadedData(data: List<CoroutineTest>?) {
        binding?.apply {
            if (data != null) {
                coroutineTestAdapter.setItemList(data.toMutableList())
            } else {
                coroutineTestAdapter.setItemList(null)
            }
        }
    }

    private fun showLoadedCommentData(data: List<CoroutineComment>?) {
        Log.d("herehere", "herehere")
        binding?.apply {
            if (data != null) {
                coroutineCommentAdapter.setItemList(data.toMutableList())
            } else {
                coroutineCommentAdapter.setItemList(null)
            }
        }
    }

    private fun onOffLoadingImage(flag: Boolean) {
        binding?.apply {
            if (flag) pbCoroutineLoading.visibility = View.VISIBLE
            else pbCoroutineLoading.visibility = View.GONE
        }
    }

    private fun showRvTest() {
        binding?.rvCoroutineTest?.visibility = View.VISIBLE
        binding?.rvCoroutineComment?.visibility = View.GONE
    }

    private fun showRvComment() {
        binding?.rvCoroutineComment?.visibility = View.VISIBLE
        binding?.rvCoroutineTest?.visibility = View.GONE
    }

    private fun showToastMessage(msg:String?){
        Toast.makeText(activityContext, msg, Toast.LENGTH_SHORT).show()
    }

    private fun initObserver() {
        coroutineTestViewModel.apply {
            coroutineTestData.observe(viewLifecycleOwner) {
                when (it.status) {
                    CoroutineStatus.LOADING -> {
                        onOffLoadingImage(true)
                    }

                    CoroutineStatus.SUCCESS -> {
                        showRvTest()
                        onOffLoadingImage(false)
                        showLoadedData(it.data)
                    }

                    CoroutineStatus.ERROR -> {
                        onOffLoadingImage(false)
                    }

                    CoroutineStatus.RELOAD -> {
                        showLoadedData(it.data)
                        onOffLoadingImage(false)
                    }
                }
            }

            coroutineTestLocalData.observe(viewLifecycleOwner) {
                when (it.status) {
                    CoroutineStatus.LOADING -> {
                        onOffLoadingImage(true)
                    }

                    CoroutineStatus.SUCCESS -> {
                        showRvTest()
                        onOffLoadingImage(false)
                        showLoadedData(it.data)
                    }

                    CoroutineStatus.ERROR -> {
                        onOffLoadingImage(false)
                    }

                    CoroutineStatus.RELOAD -> {
                        showLoadedData(it.data)
                        onOffLoadingImage(false)
                    }
                }
            }


            numOfData.observe(viewLifecycleOwner) {
                if (binding?.tvCoroutineArchitectureDataType?.text?.contains("Local") == true) {
                    binding?.tvCoroutineArchitectureDataType?.text = "Local Data, num of data : $it"
                }
            }

            // Flow 부분 시작
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        coroutineCommentData.collect {
                            when (it.status) {
                                CoroutineStatus.LOADING -> {
                                    onOffLoadingImage(true)
                                }

                                CoroutineStatus.SUCCESS -> {
                                    showRvComment()
                                    onOffLoadingImage(false)
                                    showLoadedCommentData(it.data)
                                }

                                CoroutineStatus.ERROR -> {
                                    onOffLoadingImage(false)
                                    showToastMessage(it.message)
                                }

                                CoroutineStatus.RELOAD -> {
                                    onOffLoadingImage(false)
                                }
                            }
                        }
                    }

                    launch {
                        coroutineCommentDataFromLocal.collect {
                            when (it.status) {
                                CoroutineStatus.LOADING -> {
                                    onOffLoadingImage(true)
                                }

                                CoroutineStatus.SUCCESS -> {
                                    showRvComment()
                                    onOffLoadingImage(false)
                                    showLoadedCommentData(it.data)
                                }

                                CoroutineStatus.ERROR -> {
                                    onOffLoadingImage(false)
                                }

                                CoroutineStatus.RELOAD -> {
                                    onOffLoadingImage(false)
                                }
                            }
                        }
                    }
                }

            }
        }
    }

}
