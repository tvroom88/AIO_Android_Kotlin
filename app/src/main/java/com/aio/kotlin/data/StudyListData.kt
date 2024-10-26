package com.aio.kotlin.data

import com.aio.kotlin.models.StudyList
import com.aio.kotlin.models.StudyList.StudyCategory
import com.aio.kotlin.models.StudyList.StudyFragmentList
import com.aio.kotlin.studylist.architecturepattern.MvvmSimpleExample
import com.aio.kotlin.studylist.backgroundwork.multithread.MultiThreadFragment
import com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.RxJavaBaseClassesFragment
import com.aio.kotlin.studylist.backgroundwork.rx.basic.RxJavaBasicFragment
import com.aio.kotlin.studylist.backgroundwork.rx.operators.RxjavaOperators
import com.aio.kotlin.studylist.jetpack.binding.databinding.DataBindingExampleFragment
import com.aio.kotlin.studylist.jetpack.binding.viewbinding.ViewBindingExampleFragment
import com.aio.kotlin.studylist.jetpack.compose.layouts.ComposeALayouts
import com.aio.kotlin.studylist.jetpack.compose.layouts.ComposeBList
import com.aio.kotlin.studylist.recyclerview.RecyclerViewExampleFragment

class StudyListData {

    // 안드로이드 학습 리스트 데이터
    fun setStudyList(): MutableList<StudyList> {
        return mutableListOf(
            addJetPack(),
            StudyFragmentList(
                "RecyclerView",
                RecyclerViewExampleFragment().getFullFragmentName(),
                2
            ),
            addAsyncExample(),
            StudyFragmentList("Mvvm Simple Example", MvvmSimpleExample().getFullFragmentName(), 2),
            addComposeable()
        )
    }

    // JetPack 리스트 추가
    private fun addJetPack(): StudyCategory {
        val jetpackCategory = StudyCategory("Jetpack Example")
        jetpackCategory.studyList.addAll(
            mutableListOf(
                StudyFragmentList(
                    "DataBinding",
                    DataBindingExampleFragment().getFullFragmentName(),
                    2,
                    getUrl(0),
                ),
                StudyFragmentList(
                    "ViewBinding",
                    ViewBindingExampleFragment().getFullFragmentName(),
                    2

                )
            )
        )
        return jetpackCategory
    }

    // 비동기 방식 데이터 추가
    private fun addAsyncExample(): StudyCategory {
        val asyncCategory = StudyCategory("AsyncTask Example")
        asyncCategory.studyList.addAll(
            mutableListOf(
                StudyFragmentList(
                    "Thread",
                    MultiThreadFragment().getFullFragmentName(),
                    2
                ),
                StudyFragmentList(
                    "RxJavaBasic",
                    RxJavaBasicFragment().getFullFragmentName(),
                    2
                ),
                StudyFragmentList(
                    "RxJava Base Classes",
                    RxJavaBaseClassesFragment().getFullFragmentName(),
                    2
                ),
                StudyFragmentList(
                    "RxJava Operators",
                    RxjavaOperators().getFullFragmentName(),
                    2
                )
            )
        )
        return asyncCategory
    }

    // WebView url list
    private fun getUrl(idx: Int): String {
        val urlList = arrayListOf(
            "https://from-android-to-server.tistory.com/51"
        )

        return urlList[idx]
    }

    private fun addComposeable(): StudyCategory {
        val jetpackCategory = StudyCategory("Jetpack Example")
        jetpackCategory.studyList.addAll(
            mutableListOf(
                StudyList.StudyActivityList(
                    "DiverseLayouts",
                    ComposeALayouts::class
                ),
                StudyList.StudyActivityList(
                    "List",
                    ComposeBList::class
                )
            )
        )
        return jetpackCategory
    }
}
