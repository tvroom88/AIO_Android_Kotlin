package com.aio.kotlin.utils.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.aio.kotlin.R
import com.aio.kotlin.databinding.LayoutAlertDialogBinding

class CustomDialog(
    private val mContext: Context
) : Dialog(mContext), View.OnClickListener {

    private lateinit var binding: LayoutAlertDialogBinding

    var title: String? = null
    var msg: String? = null

    var mNBtText: String = ""
    var mPBtText: String = ""

    var mPListener: View.OnClickListener? = null
    var mNListener: View.OnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayoutAlertDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        (findViewById<View>(R.id.tv_title) as TextView).text = title
        (findViewById<View>(R.id.tv_msg) as TextView).text = msg

        // 버튼 설정
        if (mNListener == null) { // 버튼 1개인 경우
            binding.btTwo.visibility = View.GONE
            binding.btOne.visibility = View.VISIBLE
            binding.btOne.setOnClickListener(this)
            if (mPBtText.isNotEmpty()) {
                binding.labelOne.text = mPBtText
            }
        } else { // 버튼 2개인 경우
            binding.btOne.visibility = View.GONE
            binding.btTwo.visibility = View.VISIBLE
            binding.btPositive.setOnClickListener(this)
            binding.btNegative.setOnClickListener(this)

            if (mNBtText.isNotEmpty()) {
                binding.labelNegative.text = mNBtText
            }

            if (mPBtText.isNotEmpty()) {
                binding.labelPositive.text = mPBtText
            }
        }

        // 닫기 버튼
        binding.btnClose.setOnClickListener {
            dismiss()
            if (mNListener == null) {
                mPListener?.onClick(null)
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.bt_negative -> {
                if (mContext is Activity && !mContext.isFinishing) {
                    dismiss()
                }
                mNListener?.onClick(view)
            }

            R.id.bt_one, R.id.bt_positive -> {
                if (mContext is Activity && !mContext.isFinishing) {
                    dismiss()
                }
                mPListener?.onClick(view)
            }
        }
    }

}