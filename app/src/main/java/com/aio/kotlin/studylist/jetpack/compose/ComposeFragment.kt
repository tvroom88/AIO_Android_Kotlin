package com.aio.kotlin.studylist.jetpack.compose

import android.os.Bundle
import android.util.Log
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentComposeBinding
import com.aio.kotlin.studylist.jetpack.compose.layouts.LayoutScreen
import com.aio.kotlin.studylist.jetpack.compose.modifier.ModifierScreen
import com.aio.kotlin.studylist.jetpack.compose.sideeffect.SideEffectScreen
import com.aio.kotlin.studylist.jetpack.compose.state.basic.BasicStateScreen
import com.aio.kotlin.studylist.jetpack.compose.state.samguozhi.SamguozhiScreen
import com.aio.kotlin.studylist.jetpack.compose.webview.MainWebScreen

class ComposeFragment : ViewBindingBaseFragment<FragmentComposeBinding>() {

    private var num: Int = 0

    override fun getViewBinding(): FragmentComposeBinding =
        FragmentComposeBinding.inflate(layoutInflater)

    override fun initContentInOnViewCreated() {
        num = arguments?.getInt(ARG_NUM) ?: 0
        num = CUR_NUM

        Log.d("ComposeFragment", "initContentInOnViewCreated - num : $num")

        binding.composeView.apply {
            setContent {
                when (num) {
                    0 -> LayoutScreen()
                    1 -> SamguozhiScreen()
                    2 -> ModifierScreen()
                    3 -> MainWebScreen()
                    4 -> BasicStateScreen()
                    5 -> SideEffectScreen(activityContext)
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
