package com.sourcey.materiallogindemo.keywords.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.sourcey.materiallogindemo.R

class RegisterInputScreen {
    private val inputName = onView(withId(R.id.input_name))
    private val inputAddress = onView(withId(R.id.input_address))
    private val inputEmail = onView(withId(R.id.input_email))
    private val inputMobile = onView(withId(R.id.input_mobile))
    private val inputPassword = onView(withId(R.id.input_password))
    private val inputConfirmPassword = onView(withId(R.id.input_reEnterPassword))
    private val btnSubmit = onView(withId(R.id.btn_signup))

    fun iInputName(txt: String) {
        inputName.perform(typeText(txt))
    }

    fun iInputAddress(txt: String) {
        inputAddress.perform(typeText(txt), closeSoftKeyboard())
    }

    fun iInputEmail(txt: String) {
        inputEmail.perform(typeText(txt), pressImeActionButton())
    }

    fun iInputMobile(txt: String) {
        inputMobile.perform(scrollTo(), typeText(txt), closeSoftKeyboard())
    }

    fun iInputPassword(txt: String) {
        inputPassword.perform(scrollTo(), typeText(txt), closeSoftKeyboard())
    }

    fun iInputConfirmPassword(txt: String) {
        inputConfirmPassword.perform(scrollTo(), typeText(txt), closeSoftKeyboard())
    }

    fun iClickSubmit() {
        btnSubmit.perform(scrollTo(), click())
    }

    fun iVerifyMobileError() {
        inputMobile.perform(click()).check(matches(hasErrorText("Enter Valid Mobile Number")))
    }
}