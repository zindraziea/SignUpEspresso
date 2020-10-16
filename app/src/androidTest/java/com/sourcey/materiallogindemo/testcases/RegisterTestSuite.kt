package com.sourcey.materiallogindemo.testcases

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.sourcey.materiallogindemo.MainActivity
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.keywords.features.Login
import com.sourcey.materiallogindemo.keywords.features.ManageProfileInfo
import com.sourcey.materiallogindemo.keywords.features.Register
import com.sourcey.materiallogindemo.utils.verifyToast
import com.sourcey.materiallogindemo.utils.waitForElementToAppear
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class RegisterTestSuite {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val loginFeature = Login()
    private val registerFeature = Register()
    private val manageProfileInfo = ManageProfileInfo()

    @Test
    fun tc001RegisterSuccess() {
        val txtWelcome = onView(withText("Welcome!"))
        val btnLinkSignUp = onView(withId(R.id.link_signup))

        // test data
        val name = "abcd"
        val address = "abcd"
        val email = "email@email.com"
        val mobile = "0889999999"
        val password = "112233"
        val confirmPassword = "112233"

        // goto register input screen
        btnLinkSignUp.perform(click())
//        onView(withText("No account yet? Create one")).perform(click())
//        onView(anyOf(withId(R.id.link_signup), withText("No account yet? Create one"))).perform(click())

        // input info and submit
        onView(withId(R.id.input_name)).perform(typeText(name))
        onView(withId(R.id.input_address)).perform(typeText(address), pressImeActionButton())
        onView(withId(R.id.input_email)).perform(typeText(email))
        onView(withId(R.id.input_mobile)).perform(scrollTo(), typeText(mobile))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.input_password)).perform(typeText(password))
        onView(withId(R.id.input_reEnterPassword)).perform(scrollTo(), typeText(confirmPassword), closeSoftKeyboard())
        onView(withId(R.id.btn_signup)).perform(click())

        // verify profile screen
        waitForElementToAppear(txtWelcome)
        txtWelcome.check(matches(isDisplayed()))

        // verify profile screen
        onView(withText(email)).check(matches(isDisplayed()))
        onView(allOf(withContentDescription("info-name"), withText(name))).check(matches(isDisplayed()))
        onView(withContentDescription("info-name")).check(matches(allOf(isDisplayed(), withText(name))))

        onView(allOf(hasSibling(withText("Name")), withText(name))).check(matches(isDisplayed()))
        onView(allOf(hasSibling(withText("Name")), withParentIndex(1))).check(matches(allOf(isDisplayed(), withText(name))))

        onView(allOf(withParent(withId(R.id.rowName)), withParentIndex(1))).check(matches(allOf(isDisplayed(), withText(name))))

        onView(allOf(withText(name), withParent(withId(R.id.rowName)))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.rowName))).check(matches(withChild(withText(name))))

        onView(withContentDescription("info-email")).check(matches(allOf(isDisplayed(), withText(email))))
        onView(withContentDescription("info-address")).check(matches(allOf(isDisplayed(), withText(address))))
        onView(withContentDescription("info-mobile")).check(matches(allOf(isDisplayed(), withText(mobile))))
    }

    @Test
    fun tc001RegisterSuccessOOP() {
        val name = "abcd"
        val address = "abcd"
        val email = "email@email.com"
        val mobile = "0889999999"
        val password = "112233"
        val confirmPassword = "112233"

        registerFeature.registerSuccess(name, address, email, mobile, password, confirmPassword)
//        loginFeature.gotoRegisterInputScreen()
//        registerFeature.enterRegisterInfo(name, address, email, mobile, password, confirmPassword)
//        manageProfileInfo.verifyProfileInfo(name, address, email, mobile)
    }

    @Test
    fun tc002RegisterFailInvalidMobileNumber() {
        val name = "abcd"
        val address = "abcd"
        val email = "email@email.com"
        val mobile = "088999991"
        val password = "112233"
        val confirmPassword = "112233"

        loginFeature.gotoRegisterInputScreen()
        registerFeature.enterRegisterInfo(name, address, email, mobile, password, confirmPassword)
        registerFeature.verifyMobileError()
        verifyToast("Login failed", mActivityTestRule)
    }

    @Test
    fun tc003UpdateProfileSuccessOOP() {
        val name = "abcd"
        val address = "abcd"
        val email = "email@email.com"
        val mobile = "0889999999"
        val password = "112233"
        val confirmPassword = "112233"

        val newName = "Kanittha"
        val newEmail = "newemail@email.com"
        val newAddress = "New Address 22/33"
        val newMobile = "0887878890"

        // register success
//        loginFeature.gotoRegisterInputScreen()
//        registerFeature.enterRegisterInfo(name, address, email, mobile, password, confirmPassword)
//        manageProfileInfo.verifyProfileInfo(name, address, email, mobile)
        registerFeature.registerSuccess(name, address, email, mobile, password, confirmPassword)
        // update info
        manageProfileInfo.gotoUpdateProfileScreen()
        manageProfileInfo.enterProfileInfo(newName, newEmail, newAddress, newMobile)
        manageProfileInfo.clickSaveUpdateProfile()

        // validate info
        manageProfileInfo.verifyProfileInfo(newName, newAddress, newEmail, newMobile)
    }

    @Test
    fun tc003() {
        val userInfo = hashMapOf(
                "name" to "abc",
                "address" to "abcd",
                "email" to "email@email.com",
                "mobile" to "0886056051",
                "password" to "999999",
                "confirmPassword" to "999999"
        )

        val userInfoNew = hashMapOf(
                "name" to "accb",
                "address" to "222/111 Bangkok 10900",
                "email" to "email@email.com",
                "mobile" to "0886056051",
                "password" to "999999",
                "confirmPassword" to "999999"
        )

        registerFeature.registerSuccess2(userInfo)
        manageProfileInfo.gotoUpdateProfileScreen()
        manageProfileInfo.enterProfileInfo2(userInfoNew)
        manageProfileInfo.clickSaveUpdateProfile()
        Thread.sleep(5000)
    }
}