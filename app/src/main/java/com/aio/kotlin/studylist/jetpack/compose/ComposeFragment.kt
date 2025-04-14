package com.aio.kotlin.studylist.jetpack.compose

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentComposeBinding
import com.aio.kotlin.studylist.jetpack.compose.layouts.LayoutScreen
import com.aio.kotlin.studylist.jetpack.compose.modifier.ModifierScreen
import com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.BasicMvvmScreen
import com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.composeBasicUser.ComposeBasicUserViewModel
import com.aio.kotlin.studylist.jetpack.compose.mvvm.basic.stopwatch.StopWatchViewModel
import com.aio.kotlin.studylist.jetpack.compose.sideeffect.SideEffectScreen
import com.aio.kotlin.studylist.jetpack.compose.state.basic.BasicStateScreen
import com.aio.kotlin.studylist.jetpack.compose.state.samguozhi.SamguozhiScreen
import com.aio.kotlin.studylist.jetpack.compose.webview.MainWebScreen
import com.aio.kotlin.studylist.jetpack.paging.github.data.GitHubRetrofitInstance
import com.aio.kotlin.studylist.jetpack.paging.github.ui.GitHubUserList
import com.aio.kotlin.studylist.jetpack.paging.github.ui.GitHubViewModel
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.PagingRepository
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.data.local.PagingRoomDatabase
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.ui.PagingComposeScreen
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.ui.PagingViewModel
import com.aio.kotlin.studylist.jetpack.paging.jsonplaceholder.ui.PagingViewModelFactory

class ComposeFragment : ViewBindingBaseFragment<FragmentComposeBinding>() {

    private var num: Int = 0

    private val stopWatchViewModel: StopWatchViewModel by viewModels()
    private val composeBasicUserViewModel: ComposeBasicUserViewModel by viewModels()

    // Paging에서 사용한 ViewModel
    private val githubViewModel: GitHubViewModel by viewModels {
        GitHubRetrofitInstance.provideViewModelFactory()
    }

    private lateinit var pagingViewModel: PagingViewModel


    override fun getViewBinding(): FragmentComposeBinding =
        FragmentComposeBinding.inflate(layoutInflater)

    override fun initContentInOnViewCreated() {
        num = arguments?.getInt(ARG_NUM) ?: 0
        num = CUR_NUM

        Log.d("ComposeFragment", "initContentInOnViewCreated - num : $num")

        // DB와 Repository 초기화
        val db = PagingRoomDatabase.getInstance(requireContext())!!
        val pagingItemDao = db.pagingItemDao()
        val repository = PagingRepository(pagingItemDao)

        // ViewModel 초기화
        pagingViewModel = ViewModelProvider(
            this,
            PagingViewModelFactory(repository)
        )[PagingViewModel::class.java]


        binding.composeView.apply {
            setContent {
                when (num) {
                    // Compose 자체 내용들
                    0 -> LayoutScreen()
                    1 -> SamguozhiScreen()
                    2 -> ModifierScreen()
                    3 -> MainWebScreen()
                    4 -> BasicStateScreen()
                    5 -> SideEffectScreen(activityContext)
                    6 -> BasicMvvmScreen(stopWatchViewModel, composeBasicUserViewModel)

                    // Compose 제외한 부분들
                    101 -> GitHubUserList(githubViewModel) // 1. Paging with github list
                    102 -> PagingComposeScreen(pagingViewModel)

                }
            }
        }
    }

    companion object {
        private const val ARG_NUM = "arg_num"
        var CUR_NUM = 0

        fun newInstance(num: Int): ComposeFragment {
            Log.d("ComposeFragment", "newInstance - num : $num")

            return ComposeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_NUM, num)
                }
            }
        }
    }
}
