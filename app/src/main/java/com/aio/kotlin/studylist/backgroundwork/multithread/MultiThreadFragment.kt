package com.aio.kotlin.studylist.backgroundwork.multithread

import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentMultiThreadBinding
import com.aio.kotlin.utils.CheckThreadUtils
import com.aio.kotlin.utils.HandlerUtil
import java.lang.Thread.sleep
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
        binding?.apply{

            // 1. Thread를 직접 사용하는 방식
            btnMultithreadThreadTest.setOnClickListener {
                addThread()
            }

            // 2. Runnable을 만든 후, Thread에 Runnable을 넘겨서 실행하도록 한 방식
            btnMultithreadRunnableTest.setOnClickListener {
                useRunnable()
            }

            // 3. Executor Service를 활용해 Thread Pool을 만들어 Runnable을 submit 하는 방식
            btnMultithreadExecutorTest.setOnClickListener {
                useExecutor()
            }

            // 4. Executor Service의 주어진 Thread 숫자보다 많은 Task가 있을 경우
            btnMultithreadExecutorTestMore.setOnClickListener {
                testMoreExecutor()
            }
        }


        checkThreadUtils.getThreadList(activityContext)
    }

    // 1. Thread를 직접 사용하는 방식 (Handler.post() 사용)
    private fun addThread() {
        val thread = object : Thread() {
            override fun run() {
                for (num in 0..10) {
                    handlerUtils.handlerPost {
                        binding?.tvMultithreadThreadTest?.text = "$num"
                    }
                    sleep(1000L)
                }
            }
        }
        thread.start()
    }

    // 2. Runnable을 만든 후, Thread에 Runnable을 넘겨서 실행하도록 한 방식 (runOnUiThread() 사용)
    private fun useRunnable() {
        val runnable = Runnable {
            for (num in 0..10) {
                handlerUtils.runOnUiThread(activity) {
                    binding?.tvMultithreadRunnableTest?.text = "$num"
                }
                sleep(1000L)
            }
        }
        val runnableTestThread = Thread(runnable)
        runnableTestThread.start()
    }

    // 3. Executor Service를 이용해서 Thread Pool을 만들어 Runnable에 submit 하는 방식
    private fun useExecutor() {
        val executorService = Executors.newFixedThreadPool(2)
        val runnable = Runnable {
            for (num in 0..10) {
                handlerUtils.runOnUiThread(activity) {
                    binding?.tvMultithreadExecutorTest?.text = "$num"
                }
                sleep(1000L)
            }
        }
        executorService.submit(
            runnable
        )

        executorService.shutdown();
    }

    /**
     * 4. Executor Service 테스트
     * Thread Pool이 2개인데 Task는 2개 이상일 경우
     */
    private fun testMoreExecutor() {
        val executorService = Executors.newFixedThreadPool(2) //Thread 2
        val addRunnable: (Int) -> Runnable = { idx ->
            Runnable {
                for(num in 0..10){
                    binding?.apply {
                        handlerUtils.runOnUiThread(activity) {
                            when(idx){
                                1 -> tvMultithreadExecutorTestMore1.text = "$num"
                                2 -> tvMultithreadExecutorTestMore2.text = "$num"
                                3 -> tvMultithreadExecutorTestMore3.text = "$num"
                                4 -> tvMultithreadExecutorTestMore4.text = "$num"
                            }
                        }
                    }
                    sleep(1000L)
                }
            }
        }

        executorService.submit(addRunnable(1))
        executorService.submit(addRunnable(2))
        executorService.submit(addRunnable(3))
        executorService.submit(addRunnable(4))

        executorService.shutdown()
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