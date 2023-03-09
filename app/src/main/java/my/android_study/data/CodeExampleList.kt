package my.android_study.data

import my.android_study.fragments_code_examples.mvc_pattern.MvcPatternFragment
import my.android_study.models.CodeExampleItem

object CodeExampleList {
    val androidStudyList = listOf(
        CodeExampleItem("MVC", "https://growing-software-engineer.tistory.com/130", ),
    )

    val resultFragmentList = listOf(
        MvcPatternFragment()
    )
}