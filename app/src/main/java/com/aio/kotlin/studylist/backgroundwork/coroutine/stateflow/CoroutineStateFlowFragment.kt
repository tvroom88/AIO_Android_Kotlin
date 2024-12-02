package com.aio.kotlin.studylist.backgroundwork.coroutine.stateflow

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

        binding.btnCoroutineStateFlowStart.setOnClickListener {
            getDataFromServer()
        }

        // StateFlow 구독
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    coroutineStateFlowViewModel.coroutineData.collect { result ->
                        when (result.status) {
                            CoroutineStatus.LOADING -> showLoadingBar()
                            CoroutineStatus.SUCCESS -> setTextView(result.data)
                            CoroutineStatus.ERROR -> showErrorMsg(result.message)
                        }
                    }
                }

                launch {
                    coroutineStateFlowViewModel.sharedFlow.collect { result ->
                        setTextView2(result)
                    }
                }

                launch {
                    coroutineStateFlowViewModel.stateInFlow.collect { result ->
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
        binding.tvCoroutineStateFlowContent1.text = str
    }

    private fun setTextView2(str: String?) {
        binding.tvCoroutineStateFlowContent2.text = str
    }

    private fun setTextView3(str: String?) {
        binding.tvCoroutineStateFlowContent3.text = str
    }

    private fun getDataFromServer() {
        coroutineStateFlowViewModel.loadData()
        coroutineStateFlowViewModel.emitSharedFlowData()
    }

    private fun showErrorMsg(msg: String?) {
        Toast.makeText(activityContext, msg, Toast.LENGTH_SHORT).show()
    }

}