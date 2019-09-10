package com.sourcey.materiallogindemo.keywords.screens

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.utils.ToastMatcher
import com.sourcey.materiallogindemo.utils.VerifyTextEditUtility
import com.sourcey.materiallogindemo.utils.withItemHint
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith

class LoginScreen : BaseScreen() {
    private val verifyTextEdit by lazy { VerifyTextEditUtility() }
    private val txtToastError = "Login failed"

//    private val btnSignUp: ViewInteraction by lazy { onView(withId(R.id.link_signup)) }
//    private val txtInputEmail: ViewInteraction by lazy { onView(withId(R.id.input_email)) }
//    private val txtInputPassword: ViewInteraction by lazy { onView(withId(R.id.input_password)) }
//    private val btnLogin: ViewInteraction by lazy { onView(withId(R.id.btn_login)) }

    private val btnSignUp: ViewInteraction by lazy { onView(withText("No account yet? Create one")) }
    private val txtInputEmail: ViewInteraction by lazy { onView(allOf(withClassName(endsWith("EditText")), isDescendantOfA(withItemHint("Email")))) }
    private val txtInputEmail2: ViewInteraction by lazy { onView(allOf(withClassName(endsWith("EditText")), withParent(withParent(withItemHint("Email"))))) }
    private val txtInputPassword: ViewInteraction by lazy { onView(withId(R.id.input_password)) }
    private val btnLogin: ViewInteraction by lazy { onView(allOf(withClassName(endsWith("Button")), withText("LOGIN"))) }


    fun iCanSeeLoginScreen() {
        waitElementUntilDisplayed(btnLogin)
        txtInputEmail.check(matches(isDisplayed()))
        btnLogin.check(matches(isDisplayed()))
    }

    fun iEnterEmail(email: String) {
        txtInputEmail.check(matches(isDisplayed())).perform(typeText(email))
        closeSoftKeyboard()
    }

    fun iEnterEmailByHint(email: String) {
        onView(allOf(withClassName(endsWith("EditText")), withParent(withParent(withItemHint("Email")))))
                .perform(typeText(email))
        closeSoftKeyboard()
    }

    fun iEnterPassword(password: String) {
        txtInputPassword.check(matches(isDisplayed())).perform(typeText(password))
        closeSoftKeyboard()
    }

    fun iEnterPasswordByHint(password: String) {
        onView(allOf(withClassName(endsWith("EditText")), isDescendantOfA(withItemHint("Password"))))
                .perform(typeText(password))
        closeSoftKeyboard()
    }

    fun iClickBtnLogin() {
        btnLogin.check(matches(isDisplayed())).perform(click())
    }

    fun iClickBtnRegister() {
        closeSoftKeyboard()
        btnSignUp.check(matches(isDisplayed())).perform(click())
    }

    fun iVerifyInputEmailErrorMessage(errorMessage: String?) {
        verifyTextEdit.verifyTextErrorMessage(txtInputEmail, errorMessage)
    }

    fun iVerifyInputPasswordErrorMessage(errorMessage: String?) {
        verifyTextEdit.verifyTextErrorMessage(txtInputPassword, errorMessage)
    }

    fun iVerifyToastErrorMessage() {
        onView(withText(txtToastError)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }
}