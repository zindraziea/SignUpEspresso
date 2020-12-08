package com.sourcey.materiallogindemo.keyword.screen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.utils.ToastMatcher
import com.sourcey.materiallogindemo.utils.waitForElementToAppear

class SignUpScreen {
    private val txtInpuName = onView(withId(R.id.input_name))
    private val txtInputAddress = onView(withId(R.id.input_address))
    private val txtInputEmail = onView(withId(R.id.input_email))
    private val txtInputMobile = onView(withId(R.id.input_mobile))
    private val txtInputPassword = onView(withId(R.id.input_password))
    private val txtReEnterPassword = onView(withId(R.id.input_reEnterPassword))
    private val btnSummit = onView(withId(R.id.btn_signup))
    private val toastLoginFail = onView(ViewMatchers.withText("Login failed")).inRoot(ToastMatcher())

    fun iInputName(name: String) {
        txtInpuName.perform(typeText(name))
    }

    fun iInputAddress(address: String) {
        txtInputAddress.perform(typeText(address), closeSoftKeyboard())
    }

    fun iInputEmail(email: String) {
        txtInputEmail.perform(typeText(email), closeSoftKeyboard())
    }

    fun iInputMobile(mobile: String) {
        txtInputMobile.perform(typeText(mobile), closeSoftKeyboard())
    }

    fun iInputPassword(password: String) {
        txtInputPassword.perform(typeText(password), closeSoftKeyboard())
    }

    fun iInputConfirmPassword(password: String) {
        txtReEnterPassword.perform(typeText(password), closeSoftKeyboard())
    }

    fun iClickSubmit() {
        btnSummit.perform(click())
    }

    fun iVerifyErrorName() {
        txtInpuName.check(matches(hasErrorText("at least 3 characters")))
    }

    fun iVerifyErrorAddress() {
        txtInputAddress.check(matches(hasErrorText("Enter Valid Address")))
    }

    fun iVerifyErrorEmail() {
        txtInputEmail.check(matches(hasErrorText("enter a valid email address")))
    }

    fun iVerifyErrorMobile() {
        txtInputMobile.check(matches(hasErrorText("Enter Valid Mobile Number")))
    }

    fun iVerifyErrorPassword() {
        txtInputPassword.check(matches(hasErrorText("between 4 and 10 alphanumeric characters")))
    }

    fun iVerifyErrorComfirmPassword() {
        txtReEnterPassword.check(matches(hasErrorText("Password Do not match")))
    }

    fun iVerifyThoseError() {
        waitForElementToAppear(toastLoginFail)
        toastLoginFail.check(matches(ViewMatchers.isDisplayed()))
    }
}