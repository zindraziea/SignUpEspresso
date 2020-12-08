package com.sourcey.materiallogindemo.keyword.screen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.utils.waitForElementToAppear

class LoginScreen {
    private val btnSignUp = onView(withId(R.id.link_signup))

    fun iClickSignUp() {
        btnSignUp.perform(click())
    }

    fun iVerifySignUpIsDisplay() {
        waitForElementToAppear(btnSignUp)
        btnSignUp.check(matches(isDisplayed()))
    }

}