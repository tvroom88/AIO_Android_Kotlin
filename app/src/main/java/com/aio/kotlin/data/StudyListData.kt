package com.aio.kotlin.data

import com.aio.kotlin.models.StudyList
import com.aio.kotlin.models.StudyList.StudyCategory
import com.aio.kotlin.models.StudyList.StudyFragmentList
import com.aio.kotlin.studylist.architecturepattern.MvvmSimpleExample
import com.aio.kotlin.studylist.backgroundwork.multithread.MultiThreadFragment
import com.aio.kotlin.studylist.backgroundwork.rx.operators.RxjavaOperators
import com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.RxJavaBaseClassesFragment
import com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.RxJavaBaseClassesViewModel
import com.aio.kotlin.studylist.backgroundwork.rx.basic.RxJavaBasicFragment
import com.aio.kotlin.studylist.jetpack.binding.databinding.DataBindingExampleFragment
import com.aio.kotlin.studylist.jetpack.binding.viewbinding.ViewBindingExampleFragment
import com.aio.kotlin.studylist.recyclerview.RecyclerViewExampleFragment

class StudyListData {

    // 안드로이드 학습 리스트 데이터
    fun setStudyList(): MutableList<StudyList> {
        return mutableListOf(
            addJetPack(),
            StudyFragmentList("RecyclerView", RecyclerViewExampleFragment().getFullFragmentName()),
            addAsyncExample(),
            StudyFragmentList("Mvvm Simple Example", MvvmSimpleExample().getFullFragmentName())
        )
    }

    private fun addJetPack(): StudyCategory {
        val jetpackCategory = StudyCategory("Jetpack Example")
        jetpackCategory.studyList.addAll(
            mutableListOf(
                StudyFragmentList(
                    "DataBinding",
                    DataBindingExampleFragment().getFullFragmentName()
                ),
                StudyFragmentList(
                    "ViewBinding",
                    ViewBindingExampleFragment().getFullFragmentName()
                )
            )
        )
        return jetpackCategory
    }

    private fun addAsyncExample(): StudyCategory {
        val asyncCategory = StudyCategory("AsyncTask Example")
        asyncCategory.studyList.addAll(
            mutableListOf(
                StudyFragmentList(
                    "Thread",
                    MultiThreadFragment().getFullFragmentName()
                ),
                StudyFragmentList(
                    "RxJavaBasic",
                    RxJavaBasicFragment().getFullFragmentName()
                ),
                StudyFragmentList(
                    "RxJava Base Classes",
                    RxJavaBaseClassesFragment().getFullFragmentName()
                ),
                StudyFragmentList(
                    "RxJava Operators",
                    RxjavaOperators().getFullFragmentName()
                )
            )
        )
        return asyncCategory
    }
}
