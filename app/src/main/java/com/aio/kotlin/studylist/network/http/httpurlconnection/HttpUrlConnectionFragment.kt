package com.aio.kotlin.studylist.network.http.httpurlconnection

import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentHttpUrlConnectionBinding
import com.aio.kotlin.utils.NetworkUtils
import com.aio.kotlin.utils.dialog.DialogUtils


class HttpUrlConnectionFragment : ViewBindingBaseFragment<FragmentHttpUrlConnectionBinding>() {

    private val networkUtil: NetworkUtils by lazy { NetworkUtils(activityContext) }
    override fun getViewBinding(): FragmentHttpUrlConnectionBinding {
        return FragmentHttpUrlConnectionBinding.inflate(layoutInflater)
    }

    private val getUrl = "https://jsonplaceholder.typicode.com/posts/1"
    private var postUrl = "https://jsonplaceholder.typicode.com/posts"

    override fun initContentInOnViewCreated() {

        binding.btnUrlconnectionConnectGet.setOnClickListener {
            HttpUrlConnectionBuilder.Builder(getUrl)
                .setMethod("GET")
                .setCallBack(object : HttpUrlConnectionBuilder.FutureCallback<Any> {
                    override fun onCompleted(e: Exception?, result: Any?) {
                        if (e != null) {
                            DialogUtils.showSingleButtonDialog(
                                context = activityContext,
                                title = "오류",
                                message = "${e.message}?",
                                buttonText = "확인",
                                onClickListener = null
                            )
                        } else if (result != null) {
                            binding.tvUrlconnectionResult.text = result.toString()
                        }
                    }
                })
                .build()
                .execute()
        }


        binding.btnUrlconnectionConnectPost.setOnClickListener{

            val params = mapOf(
                "title" to "foo",
                "body" to "bar",
                "userId" to "1"
            )

            val userUrl = binding.etUrlconnectionUrl.text.toString()
            if(userUrl.isNotEmpty()){
                postUrl = userUrl
            }

            HttpUrlConnectionBuilder.Builder(postUrl)
                .setMethod("POST")
                .addBody("aa", "aaa")
                .setCallBack(object : HttpUrlConnectionBuilder.FutureCallback<Any> {
                    override fun onCompleted(e: Exception?, result: Any?) {
                        if (e != null) {
                            DialogUtils.showSingleButtonDialog(
                                context = activityContext,
                                title = "오류",
                                message = "${e.message}?",
                                buttonText = "확인",
                                onClickListener = null
                            )
                        } else if (result != null) {
                            binding.tvUrlconnectionResult.text = result.toString()
                        }
                    }
                })
                .build()
                .execute()
        }
    }
}