package com.sourcey.materiallogindemo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun TC1001_RegisterSuccess() {
        onView(withId(R.id.link_signup)).perform(click())
        onView(withId(R.id.input_name)).perform(typeText("name"))
        onView(withId(R.id.input_address)).perform(typeText("address"))
        onView(withId(R.id.input_email)).perform(typeText("email@email.com"))
        onView(withId(R.id.input_mobile)).perform(scrollTo(), typeText("0889991111"))
        onView(withId(R.id.input_password)).perform(scrollTo(), typeText("112233"))
        onView(withId(R.id.input_reEnterPassword)).perform(scrollTo(), typeText("112233"))
        onView(withId(R.id.btn_signup)).perform(scrollTo(), click())
        Thread.sleep(3000)
        onView(withText("Hello world!")).check(matches(isDisplayed()))
    }
}
