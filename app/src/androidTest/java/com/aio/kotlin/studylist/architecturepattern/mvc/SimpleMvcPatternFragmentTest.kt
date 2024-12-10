package com.aio.kotlin.studylist.architecturepattern.mvc

import android.Manifest
import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import androidx.test.rule.GrantPermissionRule.grant
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import com.aio.kotlin.R
import com.aio.kotlin.activities.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 시나리오 :
 * (1) 앱에 진입 > Mvc Fragment까지 진입 > 이후 plus button 2번 & minus button 1번 클릭
 */


private const val BASIC_SAMPLE_PACKAGE = "com.aio.kotlin"
private const val LAUNCH_TIMEOUT = 5000L
private const val NUM_OF_CLICK_PLUS_BTN = 4
private const val NUM_OF_CLICK_MINUS_BTN = 2

@RunWith(AndroidJUnit4::class)
class SimpleMvcPatternFragmentTest {

    // Espresso
    // (1) plus button 4번 > minus button 2번 클릭
//    @get:Rule
//    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val permissionRule: GrantPermissionRule = grant(Manifest.permission.POST_NOTIFICATIONS)

//    @Test
//    fun enter_from_main_to_mvc_then_click_btn() {
//        waitUntilViewIsDisplayed(onView(withText(containsString("기초"))), true)
//        waitUntilViewIsDisplayed(onView(withText(containsString("Architecture"))), true)
//        waitUntilViewIsDisplayed(onView(withText(containsString("MVC"))), true)
//        waitUntilViewIsDisplayed(
//            onView(
//                allOf(
//                    withText(containsString("실습")),
//                    withParent(withId(R.id.two_btn_container))
//                )
//            ),
//            performClick = true
//        )
//        click_plusBtn_then_minusBtn()
//    }

    private fun click_plusBtn_then_minusBtn() {
        launchFragmentInContainer<SimpleMvcPatternFragment>()
        plusButton_whenClickedWithEmptyFields_thenChangesTextView()
        minusButton_whenClickedWithEmptyFields_thenChangesTextView()
    }

    //btn_detail_btm_nav_right
    private fun plusButton_whenClickedWithEmptyFields_thenChangesTextView() {
        for (num in 0 until NUM_OF_CLICK_PLUS_BTN) {
            onView(withId(R.id.btn_simple_mvc_plus))
                .check(matches(isDisplayed()))
                .perform(click())
            Thread.sleep(1000)
        }
    }

    private fun minusButton_whenClickedWithEmptyFields_thenChangesTextView() {
        for (num in 0 until NUM_OF_CLICK_MINUS_BTN) {
            onView(withId(R.id.btn_simple_mvc_minus))
                .check(matches(isDisplayed()))
                .perform(click())
            Thread.sleep(1000)
        }
    }

    private fun waitUntilViewIsDisplayed(
        view: ViewInteraction,
        performClick: Boolean = false,
        timeout: Long = 1000
    ) {
        val startTime = System.currentTimeMillis()
        val endTime = startTime + timeout

        while (System.currentTimeMillis() < endTime) {
            try {
                view.check(matches(isDisplayed()))
                if (performClick) {
                    view.perform(click())  // 클릭 실행
                }
                return // 뷰가 보이면 종료
            } catch (e: NoMatchingViewException) {
                Thread.sleep(100) // 뷰가 안 보이면 대기
            }
        }
    }

    // UI Animator
    private lateinit var device: UiDevice

    @Test
    fun enter_from_device_to_mvc_then_click_btn_w_ui_animator() {
        start_home_screen()
        abc()
    }

    private fun start_home_screen(){
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher : (com.sec.android.app.launcher)
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            BASIC_SAMPLE_PACKAGE
        )?.apply {
            // Clear out any previous instances
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    private fun abc(){
        device.wait(Until.findObject(By.textContains("기초").clazz("android.widget.TextView")),1000).click()
        device.wait(Until.findObject(By.textContains("Architecture").clazz("android.widget.TextView")), 1000).click()
        device.wait(Until.findObject(By.textContains("MVC").clazz("android.widget.TextView")), 1000).click()
        device.wait(Until.findObject(By.textContains("실습").clazz("android.widget.Button")), 1000).click()

        plusButton_whenClickedWithEmptyFields_thenChangesTextView()
        minusButton_whenClickedWithEmptyFields_thenChangesTextView()

        // 값이 다시 0으로 감소했는지 확인
        onView(withId(R.id.tv_simple_mvc_number))
            .check{ view, _ ->
                val text = (view as TextView).text.toString()
                assertEquals("${NUM_OF_CLICK_PLUS_BTN - NUM_OF_CLICK_MINUS_BTN}", text) // 텍스트가 "0"이어야 함을 확인
            }
    }
}