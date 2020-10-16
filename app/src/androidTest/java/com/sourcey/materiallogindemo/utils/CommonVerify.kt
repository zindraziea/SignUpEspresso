package com.sourcey.materiallogindemo.utils

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.sourcey.materiallogindemo.MainActivity
import org.hamcrest.Matchers

fun verifyToast(message: String, activityRule: ActivityTestRule<MainActivity>) {
    Espresso.onView(ViewMatchers.withText(message))
            .inRoot(RootMatchers.withDecorView(Matchers.not(activityRule.activity.getWindow().getDecorView())))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}
