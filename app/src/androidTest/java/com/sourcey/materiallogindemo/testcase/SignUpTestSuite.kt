package com.sourcey.materiallogindemo.testcase

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sourcey.materiallogindemo.LoginActivity
import com.sourcey.materiallogindemo.R
import com.sourcey.materiallogindemo.keyword.feature.SignupFeature
import com.sourcey.materiallogindemo.utils.ToastMatcher
import com.sourcey.materiallogindemo.utils.waitForElementToAppear
import org.hamcrest.CoreMatchers.endsWith
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SignUpTestSuite {



    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun test001_loginTestSuite() {
        val name: String = "name A"
        val address: String = "Address A"
        val email: String = "email@gmail.com"
        val mobile: String = "0962826619"
        val password: String = "11112222"
        val comfirmPassword: String = "11112222"
        SignupFeature.iSignUpNewUser(name, address, email, mobile, password, comfirmPassword)
        SignupFeature.iVerifyLoginSuccress(name, email, address, mobile)
    }

    @Test
    fun test002_singupFailNotInputName() {
        val name: String = ""
        val address: String = "Address A"
        val email: String = "email@gmail.com"
        val mobile: String = "0962826619"
        val password: String = "11112222"
        val comfirmPassword: String = "11112222"
        SignupFeature.iSignUpNewUser(name, address, email, mobile, password, comfirmPassword)
        SignupFeature.iVerifyNameError()
    }

    @Test
    fun test003_singupFailPasswordNotMatch() {
        val name: String = "Name A"
        val address: String = "Address A"
        val email: String = "email@gmail.com"
        val mobile: String = "0962826619"
        val password: String = "11112222"
        val comfirmPassword: String = "11112223"
        SignupFeature.iSignUpNewUser(name, address, email, mobile, password, comfirmPassword)
        SignupFeature.iVerifyComfirmPasswordError()
    }
}

