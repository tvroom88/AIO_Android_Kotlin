package com.aio.kotlin.base.fragment.web

import android.graphics.Color
import android.os.Bundle
import android.webkit.WebChromeClient
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentBaseWebBinding


class BaseWebFragment : ViewBindingBaseFragment<FragmentBaseWebBinding>() {

    private var mUrl: String? = null

    fun newInstance(url: String?): BaseWebFragment {
        val fragment: BaseWebFragment = BaseWebFragment()
        val args = Bundle()
        args.putString(URL, url)
        fragment.setArguments(args)
        return fragment
    }

    override fun getViewBinding(): FragmentBaseWebBinding =
        FragmentBaseWebBinding.inflate(layoutInflater)

    override fun initContentInOnViewCreated() {

        // 전달된 데이터를 가져옴
        if (arguments != null) {
            mUrl = arguments?.getString(URL);
        }


        binding.webview.apply {

            setWebViewClient(BaseWebViewClient());  // 새 창 띄우기 않기
            setWebChromeClient(WebChromeClient())
            getSettings().loadWithOverviewMode = true  // WebView 화면크기에 맞추도록 설정 - setUseWideViewPort 와 같이 써야함
            getSettings().useWideViewPort = true // wide viewport 설정 - setLoadWithOverviewMode 와 같이 써야함
            getSettings().setSupportZoom(false)  // 줌 설정 여부
            getSettings().builtInZoomControls = false;  // 줌 확대/축소 버튼 여부
            getSettings().javaScriptEnabled = true // 자바스크립트 사용여부
            getSettings().javaScriptCanOpenWindowsAutomatically = true // javascript가 window.open()을 사용할 수 있도록 설정
            getSettings().supportMultipleWindows() // 멀티 윈도우 사용 여부
            getSettings().domStorageEnabled = true  // 로컬 스토리지 (localStorage) 사용여부

            loadUrl(mUrl ?: "https://www.naver.com")

            setBackgroundColor(Color.WHITE)

        }
    }

    companion object {
        private const val URL: String = ""
    }

}