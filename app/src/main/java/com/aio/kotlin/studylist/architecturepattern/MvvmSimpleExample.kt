package com.aio.kotlin.studylist.architecturepattern

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentMvvmSimpleExampleBinding

class MvvmSimpleExample : ViewBindingBaseFragment<FragmentMvvmSimpleExampleBinding>() {

    private lateinit var mMvvmSimpleViewModel: MvvmSimpleViewModel

    override fun getViewBinding(): FragmentMvvmSimpleExampleBinding =
        FragmentMvvmSimpleExampleBinding.inflate(layoutInflater)

    override fun initContentInOnViewCreated() {
        Log.d("MvvmSimpleExample", "initContentInOnViewCreated")
        mMvvmSimpleViewModel = ViewModelProvider(requireActivity())[MvvmSimpleViewModel::class.java]

        mMvvmSimpleViewModel.someLiveData.observe(viewLifecycleOwner) {
            binding.tvMvvmsimpleNumber.text = it.toString()
        }

        binding.apply {
            btnMvvmsimplePlus.setOnClickListener { mMvvmSimpleViewModel.plus() }
            btnMvvmsimpleMinus.setOnClickListener { mMvvmSimpleViewModel.minus() }
        }
    }
}
