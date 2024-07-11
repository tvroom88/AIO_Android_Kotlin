package com.aio.kotlin.models

import java.io.Serializable
import kotlin.reflect.KClass


//data class StudyList(
//    val title: String,
//    val fragmentName: String
//) : Serializable

sealed class StudyList : Serializable {

    abstract var title: String

    data class StudyActivityList(
        override var title: String,
        val classInfo: KClass<*>
    ) : StudyList()

    data class StudyFragmentList(
        override var title: String,
        val fragmentName: String
    ) : StudyList()
}