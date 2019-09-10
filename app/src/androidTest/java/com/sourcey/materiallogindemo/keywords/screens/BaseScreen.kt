package com.sourcey.materiallogindemo.keywords.screens

import android.content.ContentValues
import android.support.design.widget.TextInputLayout
import android.util.Log
import android.view.View
import androidx.annotation.CheckResult
import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingRootException
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.TreeIterables
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.any
import java.util.concurrent.CountDownLatch

private val TIMEOUT_MILLISECONDS = 10000
private val SLEEP_MILLISECONDS = 100
private val BUFFER_MILLISECONDS = 500

open class BaseScreen {
    @CheckResult
    fun exists(interaction: ViewInteraction): Boolean {
        try {
            interaction.perform(object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return any(View::class.java)
                }

                override fun getDescription(): String {
                    return "check for existence"
                }

                override fun perform(uiController: UiController, view: View) {
                    // no op, if this is run, then the execution will continue after .perform(...)
                }
            })
            return true
        } catch (ex: AmbiguousViewMatcherException) {
            // if there's any interaction later with the same matcher, that'll fail anyway
            return true // we found more than one
        } catch (ex: NoMatchingViewException) {
            return false
        } catch (ex: NoMatchingRootException) {
            // optional depending on what you think "exists" means
            return false
        }
    }

    fun waitElementUntilDisplayed(interaction: ViewInteraction, millis: Long = TIMEOUT_MILLISECONDS.toLong()): Boolean {
        val maxAttempts = millis / SLEEP_MILLISECONDS
        var i = 0
        while (i++ < (1 + maxAttempts.toInt())) {
            try {
                interaction.check(matches(ViewMatchers.isDisplayed()))
                Thread.sleep(BUFFER_MILLISECONDS.toLong())
                return true
            } catch (e: NoMatchingViewException) {
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

    fun waitElementUntilDisplayed(viewMatcher: Matcher<View>, millis: Long = TIMEOUT_MILLISECONDS.toLong()): Boolean? {
        val found = arrayOfNulls<Boolean>(1)

        val latch = CountDownLatch(1)
        val action = object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isRoot()
            }

            override fun getDescription(): String {
                return "wait for a specific view with id <$viewMatcher> during $millis millis."
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val startTime = System.currentTimeMillis()
                val endTime = startTime + millis


                do {
                    for (child in TreeIterables.breadthFirstViewTraversal(view)) {

                        if (viewMatcher.matches(child)) {
                            Log.d(ContentValues.TAG, "perform: found match")
                            found[0] = true
                            latch.countDown()
                            return
                        }
                    }

                    uiController.loopMainThreadForAtLeast(SLEEP_MILLISECONDS.toLong())
                } while (System.currentTimeMillis() < endTime)

                found[0] = false
                latch.countDown()
            }
        }
        Espresso.onView(ViewMatchers.isRoot()).perform(action)

        latch.await()
        return found[0]
    }

    fun waitForElementToEnabled(interaction: ViewInteraction, millis: Long = TIMEOUT_MILLISECONDS.toLong()): Boolean? {
        val maxAttempts = millis / SLEEP_MILLISECONDS
        var i = 0
        while (i++ < (1 + maxAttempts.toInt())) {
            try {
                interaction.check(matches(ViewMatchers.isDisplayed()))
                interaction.check(matches(ViewMatchers.isEnabled()))
                Log.i("ViewChecker", "Not sleeping")
                Thread.sleep(200)
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
}