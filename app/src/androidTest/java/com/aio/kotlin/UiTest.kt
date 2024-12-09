package com.aio.kotlin

import android.annotation.SuppressLint
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aio.kotlin.studylist.test.UiTestActivity
import com.aio.kotlin.studylist.test.UiTestFragment
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Espresso 라이브러리와 Activity & Fragment에서 UI 테스트
 */
@RunWith(AndroidJUnit4::class)
class UiTest {

    // Activity 테스트 하는 부분
    @get:Rule
    val activityRule = ActivityScenarioRule(UiTestActivity::class.java)

    @Before
    fun before() {
        // setUp
    }

    @After
    fun after() {

    }

    @SuppressLint("CheckResult")
    @Test
    fun testActivityButtonClick() {

        // EditText
        onView(withId(R.id.et_ui_test_espresso))
            .perform(closeSoftKeyboard())
            .check(matches(isDisplayed()))

        // Button
        onView(withId(R.id.btn_ui_test_espresso))
            .perform(click())
            .check(matches(withText("Hello Espresso")))


        // TextView
        onView(withId(R.id.tv_ui_test_espresso))
            .check(matches(withText("Espresso Example")));
    }


    @SuppressLint("CheckResult")
    @Test
    fun testFragmentButtonClick() {

        val scenario = launchFragmentInContainer<UiTestFragment>()

        // EditText
        onView(withId(R.id.et_ui_test_espresso_fragment))
            .perform(closeSoftKeyboard())
            .check(matches(isDisplayed()))

        // Button
        onView(withId(R.id.btn_ui_test_espresso_fragment))
            .perform(click())
            .check(matches(withText("Hello Espresso")))


        // TextView
        onView(withId(R.id.tv_ui_test_espresso_fragment))
            .check(matches(withText("Espresso Example")));
    }
}