package com.aio.kotlin.studylist.backgroundwork.coroutine

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentCoroutineScopeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * 참고 : https://jason-api.tistory.com/25
 *
 * okhttp3와 코루틴
 * retrofit과 코루틴 등등
 * 다양한 코루틴 활용법 생각하고 찾아보기
 *
 * (1) MainThread 사용후 Worker Thread
 * (2) WorkerThread 사용후 MainThread
 */
/**
 * 1. CoroutineScope :
 */
class CoroutineScopeFragment : ViewBindingBaseFragment<FragmentCoroutineScopeBinding>() {

    private lateinit var resultTextView: TextView

    override fun getViewBinding(): FragmentCoroutineScopeBinding {
        return FragmentCoroutineScopeBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {
        binding.btnCoroutineScope.setOnClickListener {
            async()
        }
    }

    // CoroutineScope
    fun coroutineScope() {
        // 1. CoroutineScope
        CoroutineScope(Dispatchers.IO).launch {}

        // 2. viewModelScope
        // viewModelScope.launch{}

        // 3. lifecycleScope
        lifecycleScope.launch { }

        // 4. GlobalScope
        GlobalScope.launch { }
    }

    /**
     * (1) Coroutine에서 한 서버 연결 후
     * (2) 받은 데이터로 UI 업데이트 해야하는 상황
     */
    private fun usage() {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val response = fetchData("https://jsonplaceholder.typicode.com/posts/2")
                Log.d("CoroutineScopeFragment", response)

                withContext(Dispatchers.Main) {
                    updateUI(response)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showError(e)
                }
            }
        }
    }

    private fun async() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.Default).async {
                fetchData("https://jsonplaceholder.typicode.com/posts/1")
            }.await()

            binding.tvCoroutineScopeResult.text = data
        }
    }

    // okhttp3를 통해서 데이터를 가져왔다고 가정하는 부분.
    private fun fetchData(url: String): String {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        return response.body?.string() ?: ""
    }

    private fun updateUI(data: String) {}
    private fun showError(e: Exception) {}
}
