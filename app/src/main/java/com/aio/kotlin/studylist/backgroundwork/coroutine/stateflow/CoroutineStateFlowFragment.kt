package com.aio.kotlin.studylist.backgroundwork.coroutine.stateflow

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentCoroutineStateflowBinding
import kotlinx.coroutines.launch

class CoroutineStateFlowFragment : ViewBindingBaseFragment<FragmentCoroutineStateflowBinding>() {

    private val coroutineStateFlowViewModel by viewModels<CoroutineStateFlowViewModel>()

    override fun getViewBinding(): FragmentCoroutineStateflowBinding {
        return FragmentCoroutineStateflowBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {

        // 1. StateFlow + value
        binding.btnCoroutineStateFlowWValue.setOnClickListener {
            coroutineStateFlowViewModel.loadDataWithValue()
        }

        // 2. StateFlow + emit()
        binding.btnCoroutineStateFlowStartWEmit.setOnClickListener {
            coroutineStateFlowViewModel.loadDataWithEmit()
        }

        // 3. SharedFlow
        binding.btnCoroutineSharedFlow.setOnClickListener {
            coroutineStateFlowViewModel.loadSharedFlow()
        }

        // 4. StateFlow + StateIn
        binding.btnCoroutineStateFlowWStatein.setOnClickListener {
            coroutineStateFlowViewModel.makeStateFlowData()
        }

        // 5. SharedFlow + ShareIn
        binding.btnCoroutineSharedFlowWSharein.setOnClickListener {
            coroutineStateFlowViewModel.makeStateFlowData()

        }

        // StateFlow 구독
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    coroutineStateFlowViewModel.coroutineData.collect { result ->
                        Log.d("CoroutineStateFlowViewModel", "result : $result")
                        when (result.status) {
                            CoroutineStatus.LOADING -> showLoadingBar()
                            CoroutineStatus.SUCCESS -> setTextView(result.data)
                            CoroutineStatus.ERROR -> showErrorMsg(result.message)
                        }
                    }
                }

                launch {
                    coroutineStateFlowViewModel.sharedFlow.collect { result ->
                        Log.d("CoroutineStateFlowViewModel", "result $result")
                        setTextView2(result)
                    }
                }

                launch {
                    coroutineStateFlowViewModel.stateFlowWithStateIn.collect { result ->
                        setTextView3(result)
                    }
                }

                launch {
                    coroutineStateFlowViewModel.sharedFlowWithSharedIn.collect { result ->
                        setTextView3(result)
                    }
                }
            }
        }
    }

    private fun showLoadingBar() {
        binding.pbCoroutineStateFlowLoading.visibility = View.VISIBLE
    }

    private fun hideLoadingBar() {
        binding.pbCoroutineStateFlowLoading.visibility = View.GONE
    }

    private fun setTextView(str: String?) {
        hideLoadingBar()
        binding.tvCoroutineStateFlowContent.text = str
    }

    private fun setTextView2(str: String?) {
        binding.tvCoroutineSharedFlowContent.text = str
    }

    private fun setTextView3(str: String?) {
        binding.tvCoroutineStateSharedIn.text = str
    }

    private fun showErrorMsg(msg: String?) {
        Toast.makeText(activityContext, msg, Toast.LENGTH_SHORT).show()
    }

}