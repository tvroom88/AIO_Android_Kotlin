package com.aio.kotlin

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.test.core.app.ApplicationProvider

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val BASIC_SAMPLE_PACKAGE = "com.aio.kotlin"
private const val LAUNCH_TIMEOUT = 5000L
private const val STRING_TO_BE_TYPED = "UiAutomator"

@RunWith(AndroidJUnit4::class)
class UiAutomatorTest {

    private lateinit var device: UiDevice

    @Before
    fun startMainActivityFromHomeScreen() {
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
            BASIC_SAMPLE_PACKAGE)?.apply {
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

    @Test
    fun testLaunchCalculator() {
        device.findObject(By.text("기초").clazz("android.widget.TextView")).click()

        val allowButton: UiObject2 = device.wait(
            Until.findObject(By.text("허용").clazz("android.widget.Button")),
            5000 // 최대 5초 기다림
        )

        allowButton.click()

        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(1)),
            LAUNCH_TIMEOUT
        )

        // 클릭 후 3초 대기
        Thread.sleep(3000)  // 3초 대기
    }

}