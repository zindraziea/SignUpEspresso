package com.sourcey.materiallogindemo.keywords.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.sourcey.materiallogindemo.R

class ProfileScreen : BaseScreen() {
    private val screenTitle: ViewInteraction by lazy { onView(withText("Material Login Example")) }
    private val txtContent: ViewInteraction by lazy { onView(withText("Hello world!")) }
    private val btnLogOut: ViewInteraction by lazy { onView(withId(R.id.btn_logout)) }

    fun iVerifyProfileScreen() {
        waitElementUntilDisplayed(screenTitle)
        screenTitle.check(matches(isDisplayed()))
        txtContent.check(matches(isDisplayed()))
        btnLogOut.check(matches(isDisplayed()))
    }

    fun iClickBtnLogOut() {
        btnLogOut.check(matches(isDisplayed()))
        btnLogOut.perform(click())
        // btnLogOut.perform(repeatedlyUntil(click(), not(isDisplayed()), 5))
    }
}