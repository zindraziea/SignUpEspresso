package com.sourcey.materiallogindemo.utils

import android.support.design.widget.TextInputLayout
import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withItemHint(matcherText: String): Matcher<View> {
    return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {

        override fun describeTo(description: Description) {
            description.appendText("with item hint: $matcherText")
        }

        override fun matchesSafely(editTextField: TextInputLayout): Boolean {
            return matcherText.equals(editTextField.hint!!.toString(), ignoreCase = true)
        }
    }
}

fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        var currentIndex: Int = 0

        override fun describeTo(description: Description) {
            description.appendText("with index: ")
            description.appendValue(index)
            matcher.describeTo(description)
        }

        override fun matchesSafely(view: View): Boolean {
            return matcher.matches(view) && currentIndex++ == index
        }
    }
}