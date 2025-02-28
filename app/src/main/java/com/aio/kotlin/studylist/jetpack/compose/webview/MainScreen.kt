package com.aio.kotlin.studylist.jetpack.compose.webview

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun MainWebScreen() {
    val url = "https://www.naver.com"
    CustomWebView(url)
}


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CustomWebView(url: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true // JavaScript 활성
                settings.domStorageEnabled = true // 로컬 저장소 활성화
                webViewClient = WebViewClient() // 기본 웹뷰 클라이언트 설정
                loadUrl(url)
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        }
    )
}
