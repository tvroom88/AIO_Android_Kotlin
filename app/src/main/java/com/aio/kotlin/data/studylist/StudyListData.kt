package com.aio.kotlin.data.studylist

import com.aio.kotlin.models.StudyList
import com.aio.kotlin.models.StudyList.StudyCategory
import com.aio.kotlin.models.StudyList.StudyFragmentList
import com.aio.kotlin.studylist.architecturepattern.mvc.SimpleMvcPatternFragment
import com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.ui.main.MvvmAdvancedExample
import com.aio.kotlin.studylist.jetpack.binding.databinding.DataBindingExampleFragment
import com.aio.kotlin.studylist.jetpack.binding.viewbinding.ViewBindingExampleFragment
import com.aio.kotlin.studylist.jetpack.compose.ComposeFragment
import com.aio.kotlin.studylist.jetpack.datastore.DataStoreFragment
import com.aio.kotlin.studylist.network.http.httpurlconnection.HttpUrlConnectionFragment
import com.aio.kotlin.studylist.network.http.okhttp3.Okhttp3TestFragment

class StudyListData {

    // 안드로이드 학습 리스트 데이터
    fun setStudyList(): MutableList<StudyList> {
        return mutableListOf(
            addJetPack(),
            addDI(),
            AsyncExample().addAsyncExample(),
            addArchitecture(),
            addNetworkConnect(),
            addComposable(),

            // StudyFragmentList(
//                "RecyclerView",
//                RecyclerViewExampleFragment().getFullFragmentName(),
//                2
//            ),
//            StudyFragmentList("Mvvm Simple Example", MvvmSimpleExample().getFullFragmentName(), 2),
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
                ),
                StudyFragmentList(
                    "DataStore",
                    DataStoreFragment().getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/222"
                ),
                StudyFragmentList(
                    "Paging - 1",
                    ComposeFragment.newInstance(8).getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/222"
                ),
                StudyFragmentList(
                    "Paging - 2",
                    ComposeFragment.newInstance(9).getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/222"
                )

            )
        )
        return jetpackCategory
    }

    private fun addComposable(): StudyCategory {
        val jetpackCategory = StudyCategory("Composable Example")
        jetpackCategory.studyList.addAll(
            mutableListOf(
                StudyFragmentList(
                    "DiverseLayouts",
                    ComposeFragment.newInstance(0).getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/51"
                ),
                StudyFragmentList(
                    "State",
                    ComposeFragment.newInstance(1).getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/51"
                ),
                StudyFragmentList(
                    "Modifier",
                    ComposeFragment.newInstance(2).getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/51"
                ),
                StudyFragmentList(
                    "WebView",
                    ComposeFragment.newInstance(3).getFullFragmentName(),
                    1
                ),
                StudyFragmentList(
                    "State",
                    ComposeFragment.newInstance(4).getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/227"
                ),
                StudyFragmentList(
                    "SideEffect",
                    ComposeFragment.newInstance(5).getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/228"
                ),
                StudyFragmentList(
                    "Mvvm예제",
                    ComposeFragment.newInstance(6).getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/228"
                )
            )
        )
        return jetpackCategory
    }

    private fun addDI(): StudyCategory {
        val jetpackCategory = StudyCategory("Dependency Injection")
        jetpackCategory.studyList.addAll(
            mutableListOf(
                StudyFragmentList(
                    "(DI - 1편) 의존성 주입이란",
                    ViewBindingExampleFragment().getFullFragmentName(),
                    1,
                    "https://from-android-to-server.tistory.com/113"
                ),
                StudyFragmentList(
                    "(DI - 2편) 안드로이드 의존성 수동 주입",
                    ViewBindingExampleFragment().getFullFragmentName(),
                    1,
                    "https://from-android-to-server.tistory.com/180"
                ),
                StudyFragmentList(
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
                StudyFragmentList(
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

    private fun addNetworkConnect(): StudyCategory {
        val networkConnection = StudyCategory("Network Connection")
        networkConnection.studyList.addAll(
            mutableListOf(
                StudyFragmentList(
                    "HttpURLConnection",
                    HttpUrlConnectionFragment().getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/197"
                ),
                StudyFragmentList(
                    "Okhttp3",
                    Okhttp3TestFragment().getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/197"
                ),
            )
        )

        return networkConnection
    }

}
