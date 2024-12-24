package com.aio.kotlin.studylist.network.http.httpurlconnection

import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentHttpUrlConnectionBinding
import com.aio.kotlin.utils.NetworkUtils

//fragment_http_url_connection
class HttpUrlConnectionFragment : ViewBindingBaseFragment<FragmentHttpUrlConnectionBinding>() {

    val networkUtil: NetworkUtils by lazy { NetworkUtils() }
    override fun getViewBinding(): FragmentHttpUrlConnectionBinding {
        return FragmentHttpUrlConnectionBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {


        binding.btnUrlconnectionConnect.setOnClickListener {
            networkUtil.connectWithHttpURLConnection("https://www.naver.com")
        }

    }
}