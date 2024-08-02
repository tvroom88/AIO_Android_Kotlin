package com.aio.kotlin.studylist.jetpack.binding.viewbinding

import android.os.Bundle
import com.aio.kotlin.base.activity.ViewBindingBaseActivity
import com.aio.kotlin.databinding.ActivityViewBindingBinding

class ViewBindingExampleActivity : ViewBindingBaseActivity<ActivityViewBindingBinding>() {

    // getViewBinding 메서드를 구현하여 ViewBinding 인스턴스를 반환
    override fun getViewBinding(): ActivityViewBindingBinding {
        return ActivityViewBindingBinding.inflate(layoutInflater)
    }

    override fun initOnCreate() {}
}