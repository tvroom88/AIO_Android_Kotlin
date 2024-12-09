package com.aio.kotlin.studylist.architecturepattern.mvc

import android.widget.TextView
import com.aio.kotlin.base.fragment.ViewBindingBaseFragment
import com.aio.kotlin.databinding.FragmentSimpleMvcPatternBinding

class SimpleMvcPatternFragment :  ViewBindingBaseFragment<FragmentSimpleMvcPatternBinding>() {
    override fun getViewBinding(): FragmentSimpleMvcPatternBinding =
        FragmentSimpleMvcPatternBinding.inflate(layoutInflater)

    private var model = SimpleMvcModel(0)

    override fun initContentInOnViewCreated() {

        val tvNumber = binding.tvSimpleMvcNumber
        val btnPlus = binding.btnSimpleMvcPlus
        val btnMinus = binding.btnSimpleMvcMinus

        updateView(tvNumber)

        // Controller 로직
        btnPlus.setOnClickListener {
            model.plusOne()
            updateView(tvNumber)
        }

        btnMinus.setOnClickListener {
            model.minusOne()
            updateView(tvNumber)
        }
    }

    private fun updateView(tvNumber: TextView) {
        tvNumber.text = model.number.toString()
    }
}
