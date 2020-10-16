package com.sourcey.materiallogindemo.keywords.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.utils.waitForElementToAppear
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith

class UpdateProfileScreen {
    private val txtScreenTitle = onView(withText("Update Profiles"))
    private val inputName = onView(allOf(hasSibling(withText("Name")), withParentIndex(1)))
    private val inputName2 = onView(allOf(hasSibling(withText("Name")), withClassName(endsWith("EditText"))))
    private val inputEmail = onView(allOf(hasSibling(withText("Email")), withParentIndex(1)))
    private val inputAddress = onView(allOf(hasSibling(withText("Address")), withParentIndex(1)))
    private val inputMobile = onView(allOf(hasSibling(withText("Mobile")), withParentIndex(1)))
    private val btnSave = onView(withId(R.id.btn_update_profiles))

    fun iCanSeeUpdateProfileScreen() {
        waitForElementToAppear(txtScreenTitle)
        txtScreenTitle.check(matches(isDisplayed()))
    }

    fun iInputName(txt: String) {
        inputName.perform(replaceText(txt))
    }

    fun iInputEmail(txt: String) {
        inputEmail.perform(clearText(), typeText(txt), closeSoftKeyboard())
    }

    fun iInputAddress(txt: String) {
        inputAddress.perform(clearText(), typeText(txt), closeSoftKeyboard())
    }

    fun iInputMobile(txt: String) {
        inputMobile.perform(clearText(), typeText(txt), closeSoftKeyboard())
    }

    fun iClickSave() {
        btnSave.perform(click())
    }
}