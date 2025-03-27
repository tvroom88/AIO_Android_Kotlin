package com.aio.kotlin.studylist.jetpack.compose

import android.os.Bundle
import android.util.Log
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentComposeBinding
import com.aio.kotlin.studylist.jetpack.compose.layouts.LayoutScreen
import com.aio.kotlin.studylist.jetpack.compose.modifier.ModifierScreen
import com.aio.kotlin.studylist.jetpack.compose.state.samguozhi.SamguozhiScreen
import com.aio.kotlin.studylist.jetpack.compose.webview.MainWebScreen

class ComposeFragment() : ViewBindingBaseFragment<FragmentComposeBinding>() {

    private var num: Int = 0

    override fun getViewBinding(): FragmentComposeBinding =
        FragmentComposeBinding.inflate(layoutInflater)

    override fun initContentInOnViewCreated() {
        Log.d("HiHiHi", "HiHiHi")
        num = arguments?.getInt(ARG_NUM) ?: 0

        binding.composeView.apply {
            setContent {
                when (num) {
                    0 -> LayoutScreen()
                    1 -> SamguozhiScreen()
                    2 -> ModifierScreen()
                    3 -> MainWebScreen()
                }
            }
        }
    }

    companion object {
        private const val ARG_NUM = "arg_num"

        fun newInstance(num: Int): ComposeFragment {
            return ComposeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_NUM, num)
                }
            }
        }
    }
}


//class ComposeFragment(private val content:  @Composable () -> Unit) : ViewBindingBaseFragment<FragmentComposeBinding>() {
//    override fun getViewBinding(): FragmentComposeBinding =
//        FragmentComposeBinding.inflate(layoutInflater)
//
//    override fun initContentInOnViewCreated() {
//        Log.d("HiHiHi", "HiHiHi")
//        binding.composeView.setContent {
//            content() // 전달받은 Composable을 사용
//        }
//    }
//}
