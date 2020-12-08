package com.sourcey.materiallogindemo.keyword.screen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.utils.waitForElementToAppear
import org.hamcrest.CoreMatchers
import org.hamcrest.core.AllOf

class WelcomeScreen {

    private val txtWelcome = onView(withText("Welcome!"))
    private val txtName = onView(withContentDescription("info-name"))
    private val txtEmail = onView(withContentDescription("info-email"))
    private val txtAddress = onView(withContentDescription("info-address"))
    private val txtMobile = onView(withContentDescription("info-mobile"))
    private val btnLogout = onView(AllOf.allOf(withClassName(CoreMatchers.endsWith("Button")), withText("LOGOUT")))
    private val btnAlertLogoutOk = onView(withText("OK"))

    fun iVerifyWelcomeText() {
        waitForElementToAppear(txtWelcome)
        txtWelcome.check(matches(isDisplayed()))
    }

    fun iVerifyName(name: String) {
        txtName.check(matches(withText(name)))
    }

    fun iVerifyEmail(email: String) {
        txtEmail.check(matches(withText(email)))
    }

    fun iVerifyAddress(address: String) {
        txtAddress.check(matches(withText(address)))
    }

    fun iVerifyMobile(mobile: String) {
        txtMobile.check(matches(withText(mobile)))
    }

    fun iClickLogout() {
        btnLogout.perform(click())
    }

    fun iClickLogoutOk() {
        btnAlertLogoutOk.perform(click())
    }


}