package com.sourcey.materiallogindemo.keywords.screen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.utils.waitForElementToAppear

class RegisterScreen {
    private val inputName = onView(withId(R.id.input_name))
    private val inputAddress = onView(withId(R.id.input_address))
    private val inputEmail = onView(withId(R.id.input_email))
    private val inputMobile = onView(withId(R.id.input_mobile))
    private val inputPassword = onView(withId(R.id.input_password))
    private val inputConfirmPassword = onView(withId(R.id.input_reEnterPassword))
    private val btnSignUp = onView(withId(R.id.btn_signup))

    fun iInputName(txt: String) {
        waitForElementToAppear(inputName)
        inputName.perform(typeText(txt))
    }

    fun iInputAddress(txt: String) {
        inputAddress.perform(typeText(txt))
    }

    fun iInputEmail(txt: String) {
        inputEmail.perform(scrollTo(), typeText(txt))
    }

    fun iInputMobile(txt: String) {
        inputMobile.perform(scrollTo(), typeText(txt))
    }

    fun iInputPassword(txt: String) {
        inputPassword.perform(scrollTo(), typeText(txt))
    }

    fun iInputConfirmPassword(txt: String) {
        inputConfirmPassword.perform(scrollTo(), typeText(txt))
    }

    fun iClickBtnCreateAccount() {
        btnSignUp.perform(scrollTo(), click())
    }
}