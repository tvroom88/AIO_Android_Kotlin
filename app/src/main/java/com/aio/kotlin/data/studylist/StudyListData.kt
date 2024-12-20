package com.aio.kotlin.data.studylist

import com.aio.kotlin.models.StudyList
import com.aio.kotlin.models.StudyList.StudyCategory
import com.aio.kotlin.models.StudyList.StudyFragmentList
import com.aio.kotlin.studylist.architecturepattern.mvc.SimpleMvcPatternFragment
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.main.MvvmAdvancedExample
import com.aio.kotlin.studylist.jetpack.binding.databinding.DataBindingExampleFragment
import com.aio.kotlin.studylist.jetpack.binding.viewbinding.ViewBindingExampleFragment
import com.aio.kotlin.studylist.jetpack.compose.layouts.ComposeALayouts
import com.aio.kotlin.studylist.jetpack.compose.layouts.ComposeBList

class StudyListData {

    // 안드로이드 학습 리스트 데이터
    fun setStudyList(): MutableList<StudyList> {
        return mutableListOf(
            addJetPack(),
//            StudyFragmentList(
//                "RecyclerView",
//                RecyclerViewExampleFragment().getFullFragmentName(),
//                2
//            ),

            addDI(),
            AsyncExample().addAsyncExample(),
            addArchitecture(),
//            StudyFragmentList("Mvvm Simple Example", MvvmSimpleExample().getFullFragmentName(), 2),
//            addComposeable(),

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
                    "https://from-android-to-server.tistory.com/51",
                ),
                StudyFragmentList(
                    "ViewBinding",
                    ViewBindingExampleFragment().getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/51"
                )
            )
        )
        return jetpackCategory
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

    private fun addDI(): StudyCategory {
        val jetpackCategory = StudyCategory("Dependency Injection")
        jetpackCategory.studyList.addAll(
            mutableListOf(
                StudyList.StudyFragmentList(
                    "(DI - 1편) 의존성 주입이란",
                    ViewBindingExampleFragment().getFullFragmentName(),
                    1,
                    "https://from-android-to-server.tistory.com/113"
                ),
                StudyList.StudyFragmentList(
                    "(DI - 2편) 안드로이드 의존성 수동 주입",
                    ViewBindingExampleFragment().getFullFragmentName(),
                    1,
                    "https://from-android-to-server.tistory.com/180"
                ),
                StudyList.StudyFragmentList(
                    "(DI - 3편) DI 라이브러리 Hilt Annotations",
                    ViewBindingExampleFragment().getFullFragmentName(),
                    1,
                    "https://from-android-to-server.tistory.com/117"
                ),
            )
        )
        return jetpackCategory
    }


    private fun addArchitecture(): StudyCategory {
        val appArchitectrue = StudyCategory("Android Architecture")
        appArchitectrue.studyList.addAll(
            mutableListOf(
                StudyList.StudyFragmentList(
                    "MVC",
                    SimpleMvcPatternFragment().getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/197"
                ),
                StudyFragmentList(
                    "MVVM",
                    MvvmAdvancedExample().getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/197"
                ),
            )
        )

        return appArchitectrue
    }
}
