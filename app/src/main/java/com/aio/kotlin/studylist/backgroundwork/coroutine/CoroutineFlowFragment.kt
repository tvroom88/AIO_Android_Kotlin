package com.aio.kotlin.studylist.backgroundwork.coroutine

import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentCoroutineFlowBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CoroutineFlowFragment : ViewBindingBaseFragment<FragmentCoroutineFlowBinding>() {

    override fun getViewBinding(): FragmentCoroutineFlowBinding {
        return FragmentCoroutineFlowBinding.inflate(layoutInflater)
    }

    override fun initContentInOnViewCreated() {

        // 1. Producer(생산자)
        val flowProducerTest = coroutineFlowProducer()

        // 2. Intermediary(중간 연산자)
        val flowIntermediaryTest = coroutineFlowIntermediary(flowProducerTest)

        // 3. Consumer(소비자)
        CoroutineScope(Dispatchers.Main).launch {
            coroutineFlowConsumer(flowIntermediaryTest)
        }

    }

    private fun coroutineFlowProducer(): Flow<Int> {
        val flowBuilder: Flow<Int> = flow {
            for (num in 1..10) {
                emit(num)
            }
        }
        return flowBuilder
    }

    private fun coroutineFlowIntermediary(flowBuilder: Flow<Int>): Flow<Int> {
        val newFlowBuilder =
            flowBuilder
                .onEach {
                    delay(1000L)
                    binding.tvCoroutineFlowProducerNumber.text = "$it"
                } // 각 아이템마다 1초 대기
                .flowOn(Dispatchers.Main)
                .filter { it % 2 == 1 }
                .flowOn(Dispatchers.IO)
                .onEach {
                    binding.tvCoroutineFlowIntermediaryNumber.text = "filter { it % 2 == 1 } \n\n $it"
                }
                .flowOn(Dispatchers.Main)
                .map { it * 2 }
                .flowOn(Dispatchers.IO)

        return newFlowBuilder
    }


    private suspend fun coroutineFlowConsumer(flowBuilder: Flow<Int>) {
        flowBuilder.collect { value ->
            binding.tvCoroutineFlowConsumerNumber.text = "map { it * 2 } \n\n $value"
        }
    }

}