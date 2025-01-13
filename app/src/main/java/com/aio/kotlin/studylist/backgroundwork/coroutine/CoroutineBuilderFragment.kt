package com.aio.kotlin.studylist.backgroundwork.coroutine

import android.util.Log
import android.widget.TextView
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentCoroutineBuilderBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Coroutine Builder에는 4가지가 있다.
 * 1. launch
 * 2. async
 * 3. runBlocking
 * 4. withContext
 */
class CoroutineBuilderFragment : ViewBindingBaseFragment<FragmentCoroutineBuilderBinding>() {

    private lateinit var resultTextView: TextView

    override fun getViewBinding(): FragmentCoroutineBuilderBinding {
        return FragmentCoroutineBuilderBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {
        resultTextView = binding.tvCoroutineBuilderResult
        binding.apply {
            btnCoroutineBuilderLaunch.setOnClickListener {
                coroutineLaunch()
            }
            btnCoroutineBuilderAsync.setOnClickListener {
                coroutineAsync()
            }
            btnCoroutineBuilderRunblocking.setOnClickListener {
                coroutineRunBlocking()
            }
            btnCoroutineBuilderWithcontext.setOnClickListener {
                coroutineWithContext()
            }
        }

    }

    /**
     *   launch는 코루틴 블록을 만드는 코루틴 빌더중 하나입니다.
     *   launch는 현재 스레드를 차단하지 않고 새로운 코루틴을 생성할 수 있습니다.
     *   특정 결과값을 반환하지 않고 Job객체를 반환합니다.
     *
     *   Job으로는 코루틴을 취소하거나 작업이 끝날때까지 대기 시키는 등의 역할을 수행할 수 있다.
     *   - result.join() : 대기
     *   - result.cancel() : 코루틴 취소
     *
     */
    private fun coroutineLaunch() {
        val sb = StringBuilder()
        runBlocking {
            setStringWithStringBuilder(sb, "1. Thread Name : ${Thread.currentThread().name}", resultTextView)
            val result = launch {
                setStringWithStringBuilder(sb, "2. Thread Name : ${Thread.currentThread().name}", resultTextView)
                delay(1000L) // 지연 후 다시 이 코루틴을 재개
                setStringWithStringBuilder(sb, "3. Thread Name : ${Thread.currentThread().name}", resultTextView)
            }
            setStringWithStringBuilder(sb, "4. Thread Name : ${Thread.currentThread().name}", resultTextView)
            result.join()
            setStringWithStringBuilder(sb, "5. Thread Name : ${Thread.currentThread().name}", resultTextView)
        }
        setStringWithStringBuilder(sb, "6. Thread Name : ${Thread.currentThread().name}", resultTextView)
    }


    /**
     * async는 launch와 비슷하게 코루틴을 만들고 해당 코루틴에 대한 래퍼런스를 받아오는데,
     * launch - Job / async - Deferred<T>의 형태를 반환한다.
     *
     * launch는 미래의 계산 결과로 예상되는 비동기 작업에 대해 사용, 결과를 얻어서 다른 작업에 사용할 때  async의 Deferred<T>는 join()대신 await() 함수를 통해 대기하고 결과값을 받아오는 점이 차이점.
     */
    private fun coroutineAsync() {
        val myCharacter = CoroutineCharacter(null, null, null)
        val sb = StringBuilder()
        runBlocking {
            myCharacter.name = "John"
            setStringWithStringBuilder(sb, "1. myCharacter : $myCharacter", resultTextView)
            Log.d("coroutineAsync", "1. $myCharacter")

            val result = async {
                delay(1000L)
                myCharacter.weapon = "sword"
                setStringWithStringBuilder(sb, "2. myCharacter : $myCharacter", resultTextView)
                Log.d("coroutineAsync", "2. $myCharacter")
            }

            myCharacter.age = 11
            setStringWithStringBuilder(sb, "3. myCharacter : $myCharacter", resultTextView)
            Log.d("coroutineAsync", "3. $myCharacter")

            result.await()
            setStringWithStringBuilder(sb, "4. myCharacter : $myCharacter", resultTextView)
            Log.d("coroutineAsync", "4. $myCharacter")

        }
    }

    /**
     * runBlocking은 새로운 코루틴을 시작하고 완료까지 현재 스레드를 차단(점유)한다.
     *
     * runBlocking이 사용하는 스레드는 호출된 지점의 스레드를 사용하는데 Android에서 MainThread 내부에 선언시 runBlocking은 메인스레드를 차단(점유)하기 때문에
     * 5초이상 작업이 발생할 경우 시스템에 의해 ANR이 발생할 수 있다.
     */
    private fun coroutineRunBlocking() {
        val myCharacter = CoroutineCharacter(null, null, null)
        val sb = StringBuilder()
        myCharacter.name = "John"
        setStringWithStringBuilder(sb, "1.myCharacter : $myCharacter", resultTextView)
        Log.d("coroutineRunBlocking", "1. $myCharacter")

        runBlocking {
            delay(1000L)
            myCharacter.weapon = "sword"
            setStringWithStringBuilder(sb, "2. myCharacter : $myCharacter", resultTextView)
            Log.d("coroutineRunBlocking", "2. $myCharacter")

        }
        myCharacter.age = 11
        setStringWithStringBuilder(sb, "3. myCharacter : $myCharacter", resultTextView)
        Log.d("coroutineRunBlocking", "3. $myCharacter")

    }

    /**
     * async와 동일한 역할을 하는 키워드로, 결과 값을 반환하는 형태
     * async와 차이점은 async는 결과 값을 얻으로면 await()를 호출해야 하지만
     * withContext는 처음부터 결과 리턴까지 대기하는 형태
     *
     * 코루틴 내부에서 먼저 실행되게 할 수 있다.
     */
    private fun coroutineWithContext() {
        val myCharacter = CoroutineCharacter(null, null, null)
        val sb = StringBuilder()
        runBlocking {
            myCharacter.name = "John"
            setStringWithStringBuilder(sb, "myCharacter : $myCharacter", resultTextView)
            Log.d("coroutineWithContext", "1. $myCharacter")

            withContext(Dispatchers.IO) {
                delay(1000L)
                myCharacter.weapon = "sword"
                setStringWithStringBuilder(sb, "myCharacter : $myCharacter", resultTextView)
                Log.d("coroutineWithContext", "2. $myCharacter")

            }
            myCharacter.age = 11
            setStringWithStringBuilder(sb, "myCharacter : $myCharacter", resultTextView)
            Log.d("coroutineWithContext","3. $myCharacter")

        }
    }


    private fun setStringWithStringBuilder(
        sb: StringBuilder,
        str: String,
        textview: TextView
    ) {
        sb.append(str + "\n")
        textview.text = sb.toString()
    }

}

data class CoroutineCharacter(var name: String?, var age: Int?, var weapon: String?)