package com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.ui.fragment

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentMvvmAdvancedMoreExampleBinding
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.common.NetworkResult
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.GithubRepository
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.remote.RemoteDataSource
import com.aio.kotlin.studylist.architecturepattern.mvvm.advancedmore.nonhilt.data.remote.RemoteRetrofitInstance

class MvvmAdvancedMoreExample : ViewBindingBaseFragment<FragmentMvvmAdvancedMoreExampleBinding>() {

    private lateinit var viewModel: MvvmAdvancedMoreExampleViewModel

    override fun getViewBinding(): FragmentMvvmAdvancedMoreExampleBinding =
        FragmentMvvmAdvancedMoreExampleBinding.inflate(layoutInflater)

    override fun initContentInOnViewCreated() {

        val githubRepository =
            GithubRepository(RemoteDataSource(RemoteRetrofitInstance.retrofitService))
        val factory = MvvmAdvancedMoreExampleViewModelFactory(githubRepository)
        viewModel = ViewModelProvider(this, factory)[MvvmAdvancedMoreExampleViewModel::class.java]

        fetchData()
    }


    private fun fetchData() {
        val tempSince = 0
        val tempPerPage = 5
        viewModel.fetchDogResponse(tempSince, tempPerPage)
        viewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    // bind data to the view
                    Log.d("TestTestTest", "response : ${response.data?.get(0)?.url}")
                }

                is NetworkResult.Error -> {
                    // show error message
                    Log.d("TestTestTest", "error : ${response.message}")

                }

                is NetworkResult.Loading -> {
                    // show a progress bar
                    Log.d("TestTestTest", "loading : ${response.message}")
                }
            }
        }
    }
}