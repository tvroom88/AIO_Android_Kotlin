package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui.fragment

import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.base.recyclerview.BaseRecyclerViewAdapter
import com.aio.kotlin.databinding.FragmentMvvmAdvancedMoreExampleBinding
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.PokemonUiStatus
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.RecyclerViewPaginator
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.GithubRepository
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.datasource.remote.RemoteDataSource
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.datasource.remote.retrofit.RemoteRetrofitInstance
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.entity.remote.RemoteGithubModel
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui.UiState
import com.aio.kotlin.studylist.recyclerview.ExampleItemDecoration
import kotlinx.coroutines.launch

/**
 * 전반적인 구조 재정리 :
 *
 * GithubServiceApi에서 Response로 받음.
 * 1. fragment(fetchData) -> ViewModel -> Repository -> RemoteDataSource -> GithubService
 *
 * 다양한 Case 정리 :
 *
 * 구성 요소 : Hilt, Response, Result, Mapper, Flow + SharedFlow,  RxJava + LiveData 등등
 *
 * 1) Hilt 미사용 + Response 미사용 + Result 미사용 + Flow & SharedFlow
 * 2) Hilt 미사용 + Response 사용 + Result 미사용 + Flow & SharedFlow
 * 3) Hilt 미사용 + Response 미사용 + Result 사용 + Flow & SharedFlow
 * 4) Hilt 미사용 + Response 사용 + Result 사용 + Flow & SharedFlow
 */
class MvvmAdvancedMoreExample : ViewBindingBaseFragment<FragmentMvvmAdvancedMoreExampleBinding>() {

    private lateinit var viewModel: MvvmAdvancedMoreExampleViewModel

    private val githubAdapter by lazy { GitHubAdapter() }

    override fun getViewBinding(): FragmentMvvmAdvancedMoreExampleBinding =
        FragmentMvvmAdvancedMoreExampleBinding.inflate(layoutInflater)

    override fun initContentInOnViewCreated() {

        val githubRepository =
            GithubRepository(RemoteDataSource(RemoteRetrofitInstance.retrofitService))
        val factory = MvvmAdvancedMoreExampleViewModelFactory(githubRepository)
        viewModel = ViewModelProvider(this, factory)[MvvmAdvancedMoreExampleViewModel::class.java]

        binding.rvExample.let {
            it.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            it.adapter = githubAdapter.apply {
                onItemClickListener =
                    object : BaseRecyclerViewAdapter.OnItemClickListener<RemoteGithubModel> {
                        override fun onItemClick(
                            binding: ViewDataBinding,
                            data: RemoteGithubModel,
                            itemPosition: Int
                        ) {
                            // 클린 이벤트에 필요한 내용
                            Log.d("RecyclerViewExampleFragment", "item$itemPosition, data : $data")
                        }
                    }
            }
            it.addItemDecoration(ExampleItemDecoration(30, 60, 60))
//            it.addOnScrollListener(
//                RecyclerViewPaginator(
//                    it,
//                    { binding.pbMvvmAdvancedMoreLoading.isVisible },
//                    { viewModel.fetchNextPokemonList() },
//                    { false }
//                )
//            )
        }

        binding.btnLoadRemote.setOnClickListener {
            fetchData()
        }
    }


    private fun fetchData() {
        val tempSince = 0
        val tempPerPage = 10
        viewModel.fetchGithubData(tempSince, tempPerPage)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.githubResponse.collect {
                        when (it) {
                            UiState.Loading -> {
                                showOrHideLoadingBar(true)
                            }

                            is UiState.Success -> {
//                                showLoadedData(it.data)
                                showOrHideLoadingBar(false)
                            }

                            is UiState.Error -> {
                                Log.d("aaaaaa", "error : ${it.message}")
                            }
                            UiState.INIT -> {}

                        }
                    }
                }
                launch {
                    viewModel.githubFetchingIndex.collect {
                        Log.d("githubgithub", "page : $it")
                        viewModel.fetchGithubData(0, 10)
                    }

                }
            }
        }


//        viewModel.response.observe(this) { response ->
//            when (response) {
//                is UiState.INIT -> {} // 첫 시작
//
//                is UiState.Loading -> {
//                    showOrHideLoadingBar(true) // show a progress bar
//                }
//
//                is UiState.Success -> {
//                    // bind data to the view
//                    Log.d("TestTestTest", "response : ${response.data[0].url}")
//                    showOrHideLoadingBar(false)
//                    response.data.let {
//                        githubAdapter.setItemList(it.toMutableList())
//                    }
//                }
//
//                is UiState.Error -> {
//                    // show error message
//                    showOrHideLoadingBar(false)
//                    Log.d("TestTestTest", "error : ${response.message}")
//
//                }
//            }
//        }
    }

    private fun showOrHideLoadingBar(isLoadingBarVisible: Boolean) {
        binding.pbMvvmAdvancedMoreLoading.visibility =
            if (isLoadingBarVisible) View.VISIBLE else View.GONE
    }
}
