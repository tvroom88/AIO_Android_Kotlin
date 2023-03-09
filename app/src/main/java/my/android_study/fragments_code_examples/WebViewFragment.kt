package my.android_study.fragments_code_examples

import android.os.Bundle
import android.view.*
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import my.android_study.databinding.FragmentWebViewBinding

class WebViewFragment(var url: String) : Fragment() {

    private lateinit var binding: FragmentWebViewBinding

    //webViewFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        binding.webViewFragment.setWebViewClient(WebViewClient()); // 새 창 띄우기 않기
        binding.webViewFragment.setWebChromeClient(WebChromeClient());

        binding.webViewFragment.getSettings()
            .setLoadWithOverviewMode(true);  // WebView 화면크기에 맞추도록 설정 - setUseWideViewPort 와 같이 써야함
        binding.webViewFragment.getSettings()
            .setUseWideViewPort(true);  // wide viewport 설정 - setLoadWithOverviewMode 와 같이 써야함

        binding.webViewFragment.getSettings().setSupportZoom(false);  // 줌 설정 여부
        binding.webViewFragment.getSettings().setBuiltInZoomControls(false);  // 줌 확대/축소 버튼 여부

        binding.webViewFragment.getSettings().setJavaScriptEnabled(true); // 자바스크립트 사용여부
        binding.webViewFragment.getSettings()
            .setJavaScriptCanOpenWindowsAutomatically(true); // javascript가 window.open()을 사용할 수 있도록 설정
        binding.webViewFragment.getSettings().setSupportMultipleWindows(true); // 멀티 윈도우 사용 여부

        binding.webViewFragment.getSettings().setDomStorageEnabled(true);  // 로컬 스토리지 (localStorage) 사용여부
        binding.webViewFragment.loadUrl(url)

        binding.webViewFragment.setOnKeyListener { v, keyCode, event ->
            if (event.action !== KeyEvent.ACTION_DOWN) return@setOnKeyListener true
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (binding.webViewFragment.canGoBack()) {
                    binding.webViewFragment.goBack()
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
        val view = binding.root
        return view
    }

    fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() === MotionEvent.ACTION_UP && binding.webViewFragment.canGoBack()) {
            binding.webViewFragment.goBack()
            return true
        }
        return false
    }
}