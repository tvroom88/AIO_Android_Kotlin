package com.aio.kotlin

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aio.kotlin.studylist.test.UiTestActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.rules.ActivityScenarioRule
/**
 * Espresso 라이브러리와 Activity & Fragment에서 UI 테스트
 */
@RunWith(AndroidJUnit4::class)
class UiTest {

    // Activity 테스트 하는 부분
    @get:Rule
    val activityRule = ActivityScenarioRule(UiTestActivity::class.java)

    @Test
    fun testButtonClick() {
        // EditText에 텍스트 입력
        onView(withId(R.id.editText))
            .perform(closeSoftKeyboard())
                .check(matches(withText("Hello Espresso")))

        // 버튼 클릭
        onView(withId(R.id.button))
            .perform(click())

        // EditText에 입력된 텍스트 확인
        onView(withId(R.id.editText))
            .check(matches(withText("Hello Espresso")))
    }
}