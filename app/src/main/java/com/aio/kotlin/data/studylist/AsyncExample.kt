package com.aio.kotlin.data.studylist

import com.aio.kotlin.models.StudyList.StudyCategory
import com.aio.kotlin.models.StudyList.StudyFragmentList
import com.aio.kotlin.studylist.backgroundwork.multithread.MultiThreadFragment
import com.aio.kotlin.studylist.backgroundwork.rx.baseclasses.RxJavaBaseClassesFragment
import com.aio.kotlin.studylist.backgroundwork.rx.basic.RxJavaBasicFragment
import com.aio.kotlin.studylist.backgroundwork.rx.edittext.RxAndEtFragment
import com.aio.kotlin.studylist.backgroundwork.rx.operators.RxjavaOperators
import com.aio.kotlin.studylist.backgroundwork.rx.retrofit.ui.RxJavaRetrofitFragment

class AsyncExample {

    // 비동기 방식 데이터 추가
    fun addAsyncExample(): StudyCategory {
        val asyncCategory = StudyCategory("AsyncTask Example")
        asyncCategory.studyList.addAll(
            mutableListOf(
                StudyFragmentList(
                    "Thread",
                    MultiThreadFragment().getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/163"
                ),
                StudyFragmentList(
                    "RxJavaBasic",
                    RxJavaBasicFragment().getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/135"
                ),
                StudyFragmentList(
                    "RxJava Base Classes",
                    RxJavaBaseClassesFragment().getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/139"
                ),
                StudyFragmentList(
                    "RxJava Operators",
                    RxjavaOperators().getFullFragmentName(),
                    3,
                    "https://from-android-to-server.tistory.com/169",
                    "https://from-android-to-server.tistory.com/175"
                ),

                StudyFragmentList(
                    "RxJava With EditText",
                    RxAndEtFragment().getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/142"
                ),

                StudyFragmentList(
                    "RxJava With Retrofit",
                    RxJavaRetrofitFragment().getFullFragmentName(),
                    2,
                    "https://from-android-to-server.tistory.com/178"
                )
            )
        )
        return asyncCategory
    }
}