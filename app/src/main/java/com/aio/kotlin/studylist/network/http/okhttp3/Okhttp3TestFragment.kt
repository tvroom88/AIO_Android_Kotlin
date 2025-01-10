package com.aio.kotlin.studylist.network.http.okhttp3

import android.util.Log
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentOkhttp3TestBinding
import com.aio.kotlin.utils.JsonConverterUtils
import com.aio.kotlin.utils.NetworkUtils
import com.aio.kotlin.utils.ResponseCallBack
import okhttp3.Response

class Okhttp3TestFragment : ViewBindingBaseFragment<FragmentOkhttp3TestBinding>() {
    private val networkUtils by lazy { NetworkUtils() }
    private val connectUrl = "https://jsonplaceholder.typicode.com/posts/1"
    private val jsonConverterUtils by lazy { JsonConverterUtils() }

    override fun getViewBinding(): FragmentOkhttp3TestBinding {
        return FragmentOkhttp3TestBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {

        binding.btnOkhttp3TestWithSync.setOnClickListener {
            networkUtils.okhttp3ConnectSync(
                url = connectUrl,
                success = { response -> binding.tvOkhttp3TestResult.text = response.toString()},
                error = { e -> binding.tvOkhttp3TestResult.text = e.message },
                responseCallBack = makeNewResponseCallBack()
            )
        }

        binding.btnOkhttp3TestWithAsync.setOnClickListener {
            networkUtils.okhttp3ConnectAsync(
                url = connectUrl,
                success = { response -> binding.tvOkhttp3TestResult.text = response.toString() },
                error = { e -> binding.tvOkhttp3TestResult.text = e.message },
                responseCallBack = makeNewResponseCallBack()
            )
        }
    }

    private fun makeNewResponseCallBack(): ResponseCallBack {
        return object : ResponseCallBack {
            override fun success(response: Response) {
                val body = response.body?.string().toString()
                binding.tvOkhttp3TestResult.text = body
                val json = jsonConverterUtils.stringToJson(body)
                Log.d("makeNewResponseCallBack", "$json")
            }

            override fun error(e: Exception) {
                binding.tvOkhttp3TestResult.text = e.message
            }
        }
    }
}