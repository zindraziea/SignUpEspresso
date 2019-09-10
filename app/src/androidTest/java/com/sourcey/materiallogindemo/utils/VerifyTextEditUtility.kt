package com.sourcey.materiallogindemo.utils

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import com.sourcey.materiallogindemo.keywords.screens.BaseScreen

class VerifyTextEditUtility : BaseScreen() {

    fun verifyTextErrorMessage(targetElement: ViewInteraction, errorMessage: String?) {
        waitElementUntilDisplayed(targetElement)
        targetElement.perform(click())
        targetElement.check(matches(hasErrorText(errorMessage)))
    }
}