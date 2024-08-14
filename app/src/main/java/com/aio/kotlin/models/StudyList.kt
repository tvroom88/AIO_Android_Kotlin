package com.aio.kotlin.models

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.aio.kotlin.R
import java.io.Serializable
import kotlin.reflect.KClass

sealed class StudyList : Serializable {

    abstract var title: String

    // Study Category
    data class StudyCategory(
        override var title: String,
    ) : StudyList() {
        var studyList: MutableList<StudyList> = mutableListOf()
        var isStudyCategoryOpened = false
    }

    // Activity Study
    data class StudyActivityList(
        override var title: String,
        val classInfo: KClass<*>
    ) : StudyList()

    // Fragment Study
    data class StudyFragmentList(
        override var title: String,
        val fragmentName: String
    ) : StudyList()



    fun categoryVisibility(studyContent: StudyList) : Int{
        return if (isStudyCategoryOpened(studyContent)) View.VISIBLE else View.GONE
    }

    private fun isStudyCategoryOpened(studyContent: StudyList): Boolean {
        return if (studyContent is StudyCategory) {
            studyContent.isStudyCategoryOpened
        } else {
            false
        }
    }

    companion object {
        @JvmStatic // add this line !!
        @BindingAdapter("imageFromStudyList")
        fun bindImageFromStudyList(view: ImageView, studyList: StudyList) {
            when (studyList) {
                is StudyCategory -> {
                    // 예: 카테고리일 경우 특정 이미지를 설정
                    view.setImageResource(R.drawable.arrow_up)
                }

                is StudyActivityList -> {
                    // 예: 액티비티 리스트일 경우 특정 이미지를 설정
                    view.setImageResource(R.drawable.arrow_right)
                }

                is StudyFragmentList -> {
                    // 예: 프래그먼트 리스트일 경우 특정 이미지를 설정
                    view.setImageResource(R.drawable.arrow_right)
                }
            }
        }
    }

}
