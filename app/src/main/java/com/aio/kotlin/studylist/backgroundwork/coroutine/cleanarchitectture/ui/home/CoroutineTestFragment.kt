package com.aio.kotlin.studylist.backgroundwork.coroutine.cleanarchitectture.ui.home

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentCoroutineTestBinding
import com.aio.kotlin.databinding.FragmentRxJavaRetrofitBinding
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.ui.Status
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

    override fun initContentInOnViewCreated() {
        binding?.apply {
            btnCoroutineTestStart.setOnClickListener {
                coroutineTestViewModel.getCoroutineTestData()
            }
        }

        coroutineTestViewModel.coroutineTestData.observe(this) {

            when (it.status) {

                Status.ERROR -> {
                    Log.d("goodgood", "1")
                }

                Status.SUCCESS -> {
                    Log.d("goodgood", "2")
                }

                Status.LOADING -> {
                    Log.d("goodgood", "3")
                }
            }
        }
    }
}