package com.aio.kotlin.base.fragment.web

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class BaseWebViewClient : WebViewClient() {

    // shouldOverridingUrlLoading : 이 함수의 용도는 url의 페이지가 화면에 로드되기전 url을 가로채서 사전 작업을 할 수있다.
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return super.shouldOverrideUrlLoading(view, request)
    }

    // onPageStarted : 페이지가 로딩 시작하는 시점
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    // onPageFinished : 페이지 로딩이 종료되는 시점
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }

    // onReceivedError :  웹 리소스를 로딩하는 과정에서 에러가 났을떄 실행되는 콜백
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
    }
}