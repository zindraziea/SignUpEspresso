package com.sourcey.materiallogindemo.utility

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.sourcey.materiallogindemo.keywords.screens.BaseScreen

class VerifyTextEditUtility : BaseScreen() {

    fun verifyTextErrorMessage(targetElement: ViewInteraction, errorMessage: String?) {
        waitElementUntilDisplayed(targetElement)
        targetElement.perform(click())
        targetElement.check(matches(hasErrorText(errorMessage)))
    }
}