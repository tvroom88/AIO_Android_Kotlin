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
 * 1. CoroutineScope : CoroutineScope는 Coroutine들에 대한 Scope를 정의한다.
 * - a) 각 Coroutine들은 Scope를 가진다.
 * - b) Scope는 자식 Coroutine들의 생명주기를 관리한다.
 * - c) 자식 Coroutine이 모두 완료되어야 Scope도 완료된다.
 * - d) 내부 CoroutineContext에 Job을 반드시 포함되어야 한다.
 *
 * CoroutineScope은 Coroutine Context를 가진다. Scope를 통해 자식 코루틴에게 CoroutineContext를 전달한다.
 */
class CoroutineScopeFragment : ViewBindingBaseFragment<FragmentCoroutineScopeBinding>() {

    private lateinit var resultTextView: TextView

    override fun getViewBinding(): FragmentCoroutineScopeBinding {
        return FragmentCoroutineScopeBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {
        binding.btnCoroutineScopeWithcontext.setOnClickListener {
            launchAndWithContext()
        }
        binding.btnCoroutineScopeLaunch.setOnClickListener {
            launch()
        }
        binding.btnCoroutineScopeAsync.setOnClickListener {
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
     * CoroutineScope : launch + withContext 조합.
     *
     * 장점 :
     * (1) withContext를 사용해서 스레드 전환이 명시적이고 직관적이다.
     * (2) 하나의 CoroutineScope를 사용해 메모리 관리를 간편하게 할 수 있다.
     * (3) 구조적 동시성을 유지하므로 부모-자식 관계에서 취소 처리 등이 일관되다.
     */
    private fun launchAndWithContext() {
        var response = ""
        CoroutineScope(Dispatchers.Main).launch {
            try {
                withContext(Dispatchers.IO) {
                    response = fetchData("https://jsonplaceholder.typicode.com/posts/1")
                    Log.d("CoroutineScopeFragment", response)
                }

                updateUI(response)
            } catch (e: Exception) {
                showError(e)
            }
        }
    }

    /**
     * CoroutineScope launch + launch
     *
     * launch 같은 경우 job을 반환하고
     * job의 결과를 기다리려면 join을 통해 기다린다.
     *
     * 코루틴 scope내에 생명주기가 여러개 들어가면 복잡성 증가
     */
    private fun launch() {
        val mainScope = CoroutineScope(Dispatchers.Main)
        val ioScope = CoroutineScope(Dispatchers.IO)

        var data = ""
        mainScope.launch {
            val launchJob = ioScope.launch {
                data = fetchData("https://jsonplaceholder.typicode.com/posts/2")
            }
            launchJob.join()
            updateUI(data)
        }
    }

    /**
     * CoroutineScope launch + async
     */
    private fun async() {
        val mainScope = CoroutineScope(Dispatchers.Main)
        val ioScope = CoroutineScope(Dispatchers.IO)
        val job = mainScope.launch {
            val data = ioScope.async {
                fetchData("https://jsonplaceholder.typicode.com/posts/3")
            }.await()
            updateUI(data)
        }

//        job.join()
//        job.cancel()
    }

    // okhttp3를 통해서 데이터를 가져왔다고 가정하는 부분.
    private fun fetchData(url: String): String {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        return response.body?.string() ?: ""
    }

    private fun updateUI(data: String) {
        binding.tvCoroutineScopeResult.text = data
    }

    private fun showError(e: Exception) {
        binding.tvCoroutineScopeResult.text = e.toString()
    }
}
