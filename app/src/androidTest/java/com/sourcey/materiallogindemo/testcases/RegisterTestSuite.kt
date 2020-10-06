package com.sourcey.materiallogindemo.testcases

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.sourcey.materiallogindemo.MainActivity
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.keywords.feature.Login
import com.sourcey.materiallogindemo.keywords.feature.ManageProfileInfo
import com.sourcey.materiallogindemo.keywords.feature.Register
import com.sourcey.materiallogindemo.utils.verifyToast
import com.sourcey.materiallogindemo.utils.waitForElementToAppear
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

class RegisterTestSuite {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private val name = "abcd"
    private val address = "abcd"
    private val email = "email@email.com"
    private val mobile = "0889999999"
    private val password = "112233"
    private val confirmPassword = "112233"

    private val newName = "Peter"
    private val newEmail = "newemail@email.com"
    private val newAddress = "New Address 22/33"
    private val newMobile = "0887878890"

    private fun registerSuccess() {
        onView(withId(R.id.link_signup)).perform(click())
        onView(withId(R.id.input_name)).perform(typeText(name))
        onView(withId(R.id.input_address)).perform(typeText(address), pressImeActionButton())
        onView(withId(R.id.input_email)).perform(typeText(email))
        onView(withId(R.id.input_mobile)).perform(scrollTo(), typeText(mobile))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.input_password)).perform(typeText(password))
        onView(withId(R.id.input_reEnterPassword)).perform(scrollTo(), typeText(confirmPassword), closeSoftKeyboard())
        onView(withId(R.id.btn_signup)).perform(click())
        waitForElementToAppear(onView(withText("Welcome!")))
        onView(withText("Welcome!")).check(matches(isDisplayed()))
    }

    @Test
    fun tc001RegisterSuccess() {
        registerSuccess()

        // verify profile screen
        onView(withText(email)).check(matches(isDisplayed()))
        onView(allOf(withContentDescription("info-name"), withText(name))).check(matches(isDisplayed()))
        onView(withContentDescription("info-name")).check(matches(allOf(isDisplayed(), withText(name))))

        onView(allOf(hasSibling(withText("Name")), withText(name))).check(matches(isDisplayed()))
        onView(allOf(hasSibling(withText("Name")), withParentIndex(1))).check(matches(allOf(isDisplayed(), withText(name))))

        onView(allOf(withParent(withId(R.id.rowName)), withParentIndex(1))).check(matches(allOf(isDisplayed(), withText(name))))

        onView(allOf(withText(name), withParent(withId(R.id.rowName)))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.rowName))).check(matches(withChild(withText(name))))
        onView(allOf(withId(R.id.rowName))).check(matches(withChild(allOf(withClassName(endsWith("TextView")), withText(name)))))

        onView(withContentDescription("info-email")).check(matches(allOf(isDisplayed(), withText(email))))
        onView(withContentDescription("info-address")).check(matches(allOf(isDisplayed(), withText(address))))
        onView(withContentDescription("info-mobile")).check(matches(allOf(isDisplayed(), withText(mobile))))
    }

    @Test
    fun tc001RegisterSuccessOOP() {
        Login.gotoRegisterScreen()
        Register.inputUserInfo(name, address, email, mobile, password, confirmPassword)
        ManageProfileInfo.verifyProfileInfo(name, address, email, mobile)
    }

    @Test
    fun tc002RegisterFailInvalidMobileNumber() {
        onView(withId(R.id.link_signup)).perform(click())
        onView(withId(R.id.input_name)).perform(typeText(name))
        onView(withId(R.id.input_address)).perform(typeText(address))
        onView(withId(R.id.input_email)).perform(scrollTo(), typeText(email))
        onView(withId(R.id.input_mobile)).perform(scrollTo(), typeText("01111111"))
        onView(withId(R.id.input_password)).perform(scrollTo(), typeText(password))
        onView(withId(R.id.input_reEnterPassword)).perform(scrollTo(), typeText(confirmPassword), closeSoftKeyboard())
        onView(withId(R.id.btn_signup)).perform(click())

        onView(withId(R.id.input_mobile)).check(matches(hasErrorText("Enter Valid Mobile Number")))
        onView(withText("Login failed"))
                .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
    }

    @Test
    fun tc002RegisterFailInvalidMobileNumberOOP() {
        Login.gotoRegisterScreen()
        Register.inputUserInfo(name, address, email, "09999", password, confirmPassword)
        onView(withId(R.id.input_mobile)).check(matches(hasErrorText("Enter Valid Mobile Number")))
        verifyToast("Login failed", activityRule)
    }

    @Test
    fun tc003UpdateProfileSuccess() {
        registerSuccess()

        // verify info
        onView(allOf(withContentDescription("info-name"), withText(name))).check(matches(isDisplayed()))

        // goto edit screen
        val btnMoreOption = onView(withContentDescription("More options"))
        waitForElementToAppear(btnMoreOption)
        btnMoreOption.perform(click())

        val btnUpdate = onView(withText("Update Profiles"))
        waitForElementToAppear(btnUpdate)
        btnUpdate.perform(click())

        waitForElementToAppear(onView(withText(R.string.menu_edit_profile)))
        onView(withText(R.string.menu_edit_profile)).check(matches(isDisplayed()))

        // enter update info
        onView(allOf(hasSibling(withText("Name")), withClassName(endsWith("EditText")))).perform(clearText(), typeText(newName))
        onView(allOf(hasSibling(withText("Email")), withClassName(endsWith("EditText")))).perform(clearText(), typeText(newEmail))
        onView(allOf(hasSibling(withText("Address")), withParentIndex(1))).perform(clearText(), replaceText(newAddress))
        onView(allOf(hasSibling(withText("Mobile")), withClassName(endsWith("EditText")))).perform(replaceText(newMobile))
        onView(withId(R.id.btn_update_profiles)).perform(click())

        // verify profile after update
        waitForElementToAppear(onView(withText("User Information")))
        onView(withContentDescription("info-name")).check(matches(allOf(isDisplayed(), withText(newName))))
        onView(withContentDescription("info-email")).check(matches(allOf(isDisplayed(), withText(newEmail))))
        onView(withContentDescription("info-address")).check(matches(allOf(isDisplayed(), withText(newAddress))))
        onView(withContentDescription("info-mobile")).check(matches(allOf(isDisplayed(), withText(newMobile))))
    }

    private val userInfo = hashMapOf(
            "name" to "abc",
            "address" to "abcd",
            "email" to "email@email.com",
            "mobile" to "0886056051",
            "password" to "999999",
            "confirmPassword" to "999999"
    )

    private val userInfoNew = hashMapOf(
            "name" to "accb",
            "address" to "222/111 Bangkok 10900",
            "email" to "email@email.com",
            "mobile" to "0886056051",
            "password" to "999999",
            "confirmPassword" to "999999"
    )

    @Test
    fun tc003UpdateProfileSuccessOOP() {
        Login.gotoRegisterScreen()
        Register.inputUserInfo2(userInfo)
        ManageProfileInfo.verifyProfileInfo2(userInfo)

        ManageProfileInfo.gotoUpdateProfileScreen()
        ManageProfileInfo.enterProfileInfo(userInfoNew)
        ManageProfileInfo.clickSaveUpdateProfile()

        ManageProfileInfo.verifyProfileInfo2(userInfoNew)
    }
}