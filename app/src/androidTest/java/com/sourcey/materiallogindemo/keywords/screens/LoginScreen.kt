package com.sourcey.materiallogindemo.keywords.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.sourcey.materiallogindemo.R

class LoginScreen {
    private val btnLinkSignUp = onView(withId(R.id.link_signup))

    fun iClickBtnSignUp() {
        btnLinkSignUp.perform(click())
    }
}