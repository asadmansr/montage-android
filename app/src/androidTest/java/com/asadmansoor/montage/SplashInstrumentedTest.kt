package com.asadmansoor.montage

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.asadmansoor.montage.ui.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class SplashInstrumentedTest {

    @Rule
    @JvmField
    val testRule = ActivityTestRule<SplashActivity>(SplashActivity::class.java)

    @Test
    fun testSplashActivityUI() {
        onView(withId(R.id.iv_splash_user)).check(matches(isDisplayed()))
        onView(withId(R.id.vw_overlay)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title))
            .check(matches(withText(R.string.app_name)))
    }
}
