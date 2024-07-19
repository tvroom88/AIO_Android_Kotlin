package com.aio.kotlin.studylist.backgroundWork.multiThread

import android.util.Log
import com.aio.kotlin.R
import com.aio.kotlin.base.fragment.DataBindingBaseFragment
import com.aio.kotlin.databinding.FragmentMultiThreadBinding
import com.aio.kotlin.utils.CheckThreadUtils
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

    private val checkThreadUtils by lazy { CheckThreadUtils() }
    override fun initContentInOnViewCreated() {
        // 1. Thread를 직접 사용하는 방식
        addThread()

        // 2. Runnable을 만든 후, Thread에 Runnable을 넘겨서 실행하도록 한 방식
        useRunnable()

        // 3. Executor Service를 활용해 Thread Pool을 만들어 Runnable을 submit 하는 방식
        useExecutor()

        checkThreadUtils.getThreadList(activityContext)
    }

    // 1. Thread를 직접 사용하는 방식
    private fun addThread() {
        val thread = object : Thread() {
            override fun run() {
                sleep(1000L)
                wrapRunOnUiThread {
                    binding.tvMultithreadThreadTest1.text = "1. 새로 생성한 Thread에 의해 Text가 변경되었습니다."
                }

            }
        }
        thread.start()
    }

    // 2. Runnable을 만든 후, Thread에 Runnable을 넘겨서 실행하도록 한 방식
    private fun useRunnable() {
        val runnable = Runnable {
            sleep(1000L)
            wrapRunOnUiThread {
                binding.tvMultithreadRunnableTest.text = "2. Runnable 방식을 사용해서 Text를 변경하였습니다."
            }
        }

        val runnableTestThread = Thread(runnable)
        runnableTestThread.start()
    }

    // 3.
    private fun useExecutor() {
        val executorService = Executors.newFixedThreadPool(2)
        val runnable = Runnable {
            sleep(1000L)
            wrapRunOnUiThread {
                binding.tvMultithreadRunnableTest.text = "3. Executor 방식을 사용해서 Text를 변경하였습니다."
            }
        }
        executorService.submit(
            runnable
        )
    }

    private fun wrapRunOnUiThread(action: () -> Unit) {
        requireActivity().runOnUiThread {
            action()
        }
    }
}