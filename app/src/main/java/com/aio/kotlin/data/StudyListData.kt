package com.aio.kotlin.data

import com.aio.kotlin.models.StudyList
import com.aio.kotlin.models.StudyList.StudyCategory
import com.aio.kotlin.models.StudyList.StudyFragmentList
import com.aio.kotlin.studylist.backgroundwork.multithread.MultiThreadFragment
import com.aio.kotlin.studylist.backgroundwork.rx.basic.RxJavaBasicFragment
import com.aio.kotlin.studylist.jetpack.binding.databinding.DataBindingExampleFragment
import com.aio.kotlin.studylist.jetpack.binding.viewbinding.ViewBindingExampleFragment
import com.aio.kotlin.studylist.recyclerview.RecyclerViewExampleFragment

class StudyListData {

    fun setStudyList(): MutableList<StudyList> {
        return mutableListOf(
            addJetPack(),
            StudyFragmentList(
                "RecyclerView",
                RecyclerViewExampleFragment().getFullFragmentName()
            ),
            addAsyncExample()
        )
    }

    private fun addJetPack() : StudyCategory {
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

    private fun addAsyncExample() : StudyCategory {
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
            )
        )
        return asyncCategory
    }
}
