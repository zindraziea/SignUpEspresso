package com.sourcey.materiallogindemo.utils

import android.util.Log
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

private val TIMEOUT_MILLISECONDS = 5000
private val SLEEP_MILLISECONDS = 1000

fun waitForElementToAppear(interaction: ViewInteraction, millis: Long = TIMEOUT_MILLISECONDS.toLong()): Boolean? {
    val maxAttempts = millis / SLEEP_MILLISECONDS
    var i = 0
    while (i++ < (1 + maxAttempts.toInt())) {
        try {
            interaction.check(matches(isDisplayed()))
            Log.i("ViewChecker", "Not sleeping")
            Thread.sleep(100)
            return true
        } catch (e: Throwable) {
            e.printStackTrace()
            Log.i("ViewChecker", "sleeping")
            try {
                Thread.sleep(SLEEP_MILLISECONDS.toLong())
            } catch (e: Exception) {
                Log.i("Sleep", "sleeping error")
            }
        }
    }
    return false
}

