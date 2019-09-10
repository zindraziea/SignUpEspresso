package com.sourcey.materiallogindemo.testcases

import androidx.test.rule.ActivityTestRule
import com.sourcey.materiallogindemo.MainActivity
import com.sourcey.materiallogindemo.keywords.features.Login
import com.sourcey.materiallogindemo.keywords.features.Register
import org.junit.Rule
import org.junit.Test

class LoginTestSuite {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun TC2001_LoginFailWithEmptyEmailAndPassword() {
        Login.enterInformationAndLogin("", "")
        Login.verifyLoginFailedErrorMessage("enter a valid email address", "between 4 and 10 alphanumeric characters")
    }

    @Test
    fun TC2002_LoginFailWithInvalidEmail() {
        Login.enterInformationAndLoginWithHint("email@email", "a112233")
        Login.verifyLoginFailedErrorMessage("enter a valid email address", null)
    }

    @Test
    fun TC2003_LoginFailWithEmptyPassword() {
        Login.enterInformationAndLogin("email@email.com", "")
        Login.verifyLoginFailedErrorMessage(null, "between 4 and 10 alphanumeric characters")
    }

    @Test
    fun TC2004_LoginFailWithPasswordLessThan4Digit() {
        Login.enterInformationAndLogin("email@email.com", "a11")
        Login.verifyLoginFailedErrorMessage(null, "between 4 and 10 alphanumeric characters")
    }

    @Test
    fun TC2005_LoginFailWithPasswordMoreThan10Digit() {
        Login.enterInformationAndLogin("email@email.com", "112233445566")
        Login.verifyLoginFailedErrorMessage(null, "between 4 and 10 alphanumeric characters")
    }

    @Test
    fun TC2006_LoginFailAuthentication() {
        Login.enterInformationAndLogin("email@email.com", "b112233")
        Login.verifyLoginFailedErrorMessage(null, "enter a valid email address or password")
    }

    @Test
    fun TC2007_LoginSuccess() {
        Register.gotoRegisterScreenAndRegisterSuccess(
                "Workshop Espresso",
                "East Building",
                "email@email.com",
                "0884808890",
                "a112233",
                "a112233")
        Login.enterInformationAndLogin("email@email.com", "a112233")
        Login.verifyLogInSuccess()
    }
}