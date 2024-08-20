package com.aio.kotlin.studylist.backgroundwork.multithread

import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentMultiThreadBinding
import com.aio.kotlin.utils.CheckThreadUtils
import com.aio.kotlin.utils.HandlerUtil
import java.lang.Thread.sleep
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.Executors


/**
 * 기존 멀티 스레드 프로그래밍 방식
 * 1. Thread를 직접 사용하는 방식
 * 2. Runnable을 만든 후, Thread에 Runnable을 넘겨서 실행하도록 한 방식
 * 3. Executor Service를 활용해 Thread Pool을 만들어 Runnable을 submit 하는 방식
 */
class MultiThreadFragment :
    DataBindingBaseFragment<FragmentMultiThreadBinding>(R.layout.fragment_multi_thread) {

    private val checkThreadUtils by lazy { CheckThreadUtils() } // 현재 thread 리스트
    private val handlerUtils by lazy { HandlerUtil() } // handler util - 비동기로 UI 변경
    
    override fun initContentInOnViewCreated() {
        // 1. Thread를 직접 사용하는 방식
        addThread()

        // 2. Runnable을 만든 후, Thread에 Runnable을 넘겨서 실행하도록 한 방식
        useRunnable()

        // 3. Executor Service를 활용해 Thread Pool을 만들어 Runnable을 submit 하는 방식
        useExecutor()

        // 4. Executor Service의 주어진 Thread 숫자보다 많은 Task가 있을 경우
        testMoreExecutor()

        checkThreadUtils.getThreadList(activityContext)
    }

    // 1. Thread를 직접 사용하는 방식 (Handler.post() 사용)
    private fun addThread() {
        val thread = object : Thread() {
            override fun run() {
                sleep(1000L)
                handlerUtils.handlerPost {
                    binding?.tvMultithreadThreadTest1?.text = "1. 새로 생성한 Thread에 의해 Text가 변경되었습니다."
                }
            }
        }
        thread.start()
    }

    // 2. Runnable을 만든 후, Thread에 Runnable을 넘겨서 실행하도록 한 방식 (runOnUiThread() 사용)
    private fun useRunnable() {
        val runnable = Runnable {
            sleep(1000L)
            handlerUtils.runOnUiThread(activity) {
                binding?.tvMultithreadRunnableTest?.text = "2. Runnable 방식을 사용해서 Text를 변경하였습니다."
            }
        }

        val runnableTestThread = Thread(runnable)
        runnableTestThread.start()
    }

    // 3. Executor Service를 이용해서 Thread Pool을 만들어 Runnable에 submit 하는 방식
    private fun useExecutor() {
        val executorService = Executors.newFixedThreadPool(2)
        val runnable = Runnable {
            sleep(1000L)
            wrapRunOnUiThread {
                binding?.tvMultithreadRunnableTest?.text = "3. Executor 방식을 사용해서 Text를 변경하였습니다."
            }
        }
        executorService.submit(
            runnable
        )

        executorService.shutdown();
    }

    // 4. Executor Service 테스트
    private fun testMoreExecutor() {
        val executorService = Executors.newFixedThreadPool(2) //Thread 2

        val addRunnable: (Int, Int) -> Runnable = { num1, num2 ->
            Runnable {
                println("------  result: ${(num1 + num2)} 실행: ${getTime()} -----");
                println("------ result: " + (num1 + num2) + " (" + Thread.currentThread().name + ") ------")
            }
        }

        executorService.submit(addRunnable(1, 2))
        executorService.submit(addRunnable(1, 3))
        executorService.submit(addRunnable(1, 4))
        executorService.submit(addRunnable(1, 5))

        executorService.shutdown()
    }

    private fun wrapRunOnUiThread(action: () -> Unit) {
        activity.runOnUiThread {
            action()
        }
    }

    private fun getTime(): String {
        val formatter = SimpleDateFormat("yyyyMMdd-HH-mm-ss-SSS", Locale.KOREA)
        val date = Date();
        val currentDate = formatter.format(date)
        return currentDate
    }
}

class ThreadExample : Thread() {
    override fun run() {

    }
}

class RunnableExample : Runnable {
    override fun run() {

    }
}