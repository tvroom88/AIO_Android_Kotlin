package com.aio.kotlin.studylist.jetpack.datastore

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentDataStoreBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DataStoreFragment : ViewBindingBaseFragment<FragmentDataStoreBinding>() {

    private val viewModel: DataStoreViewModel by viewModels()

    override fun getViewBinding(): FragmentDataStoreBinding =
        FragmentDataStoreBinding.inflate(layoutInflater)


    override fun initContentInOnViewCreated() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataStoreTestFlow.collectLatest { text ->
                binding.tvDatastoreShowText.text = text
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.newDataTestFlow.collectLatest { text ->
                Log.d("HereHere", "4. newDataTestFlow - text: $text")
                binding.tvDatastoreShowText.text = text.name
            }
        }

        binding.btnDatastorePreference.setOnClickListener {
            viewModel.saveText(binding.etDatastoreShowText.text.toString())
        }

        binding.btnDatastoreProto.setOnClickListener {
            viewModel.saveProtoDataStore(binding.etDatastoreShowText.text.toString())
        }
    }
}
