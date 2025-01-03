package com.aio.kotlin.studylist.network.http.httpurlconnection

import android.util.Log
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentHttpUrlConnectionBinding
import com.aio.kotlin.utils.NetworkUtils

class HttpUrlConnectionFragment : ViewBindingBaseFragment<FragmentHttpUrlConnectionBinding>() {

    private val networkUtil: NetworkUtils by lazy { NetworkUtils() }
    override fun getViewBinding(): FragmentHttpUrlConnectionBinding {
        return FragmentHttpUrlConnectionBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {

        val params = mapOf(
            "title" to "foo",
            "body" to "bar",
            "userId" to "1"
        )

        binding.btnUrlconnectionConnectGet.setOnClickListener {
//            networkUtil.connectWithHttpURLConnection("https://www.naver.com")

//            HttpUrlConnectionBuilder.Builder("https://jsonplaceholder.typicode.com/posts")

            HttpUrlConnectionBuilder.Builder("https://jsonplaceholder.typicode.com/posts/1")
                .setMethod("POST")
                .setBody(params)  // key, value로 body 생성
                .setCallBack(object : HttpUrlConnectionBuilder.FutureCallback<Any> {
                    override fun onCompleted(e: Exception?, result: Any?) {
                        if(result != null){
                            Log.d("result", "result : $result")
                        }
                    }
                })
                .build()
                .execute()

        }
    }
}