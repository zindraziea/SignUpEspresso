package com.sourcey.materiallogindemo.keywords.screens

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.utility.VerifyTextEditUtility

class RegisterScreen : BaseScreen() {
    private val txtInputName: ViewInteraction by lazy { onView(withId(R.id.input_name)) }
    private val txtInputAddress: ViewInteraction by lazy { onView(withId(R.id.input_address)) }
    private val txtInputEmail: ViewInteraction by lazy { onView(withId(R.id.input_email)) }
    private val txtInputMobile: ViewInteraction by lazy { onView(withId(R.id.input_mobile)) }
    private val txtInputPassword: ViewInteraction by lazy { onView(withId(R.id.input_password)) }
    private val txtInputReEnterPassword: ViewInteraction by lazy { onView(withId(R.id.input_reEnterPassword)) }
    private val btnSignUp: ViewInteraction by lazy { onView(withId(R.id.btn_signup)) }
    private val verifyTextEdit by lazy { VerifyTextEditUtility() }

    fun iCanSeeRegisterScreen() {
        waitElementUntilDisplayed(txtInputName)
        txtInputName.check(matches(isDisplayed()))
    }

    fun iEnterName(name: String) {
        txtInputName.check(matches(isDisplayed())).perform(typeText(name))
        closeSoftKeyboard()
    }

    fun iEnterAddress(address: String) {
        txtInputAddress.check(matches(isDisplayed())).perform(typeText(address))
        closeSoftKeyboard()
    }

    fun iEnterEmail(email: String) {
        txtInputEmail.check(matches(isDisplayed())).perform(typeText(email))
        closeSoftKeyboard()
    }

    fun iEnterMobileNumber(mobileNo: String) {
        txtInputMobile.check(matches(isDisplayed())).perform(typeText(mobileNo))
        closeSoftKeyboard()
    }

    fun iEnterPassword(password: String) {
        txtInputPassword.check(matches(isDisplayed())).perform(typeText(password))
        closeSoftKeyboard()
    }

    fun iEnterConfirmPassword(confirmPassword: String) {
        txtInputReEnterPassword.perform(scrollTo()).check(matches(isDisplayed())).perform(typeText(confirmPassword))
        closeSoftKeyboard()
    }

    fun iClickBtnSignUp() {
        btnSignUp.check(matches(isDisplayed())).perform(click())
    }

    fun iVerifyNameError(errorMessage: String?) {
        verifyTextEdit.verifyTextErrorMessage(txtInputName, errorMessage)
    }

    fun iVerifyAddressError(errorMessage: String?) {
        verifyTextEdit.verifyTextErrorMessage(txtInputAddress, errorMessage)
    }

    fun iVerifyEmailError(errorMessage: String?) {
        verifyTextEdit.verifyTextErrorMessage(txtInputEmail, errorMessage)
    }

    fun iVerifyMobileError(errorMessage: String?) {
        verifyTextEdit.verifyTextErrorMessage(txtInputMobile, errorMessage)
    }

    fun iVerifyPasswordError(errorMessage: String?) {
        verifyTextEdit.verifyTextErrorMessage(txtInputPassword, errorMessage)
    }

    fun iVerifyConfirmPasswordError(errorMessage: String?) {
        verifyTextEdit.verifyTextErrorMessage(txtInputReEnterPassword, errorMessage)
    }
}