package com.sourcey.materiallogindemo.testcases

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sourcey.materiallogindemo.MainActivity
import com.sourcey.materiallogindemo.keywords.features.Register
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RegisterTestSuite {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val name = "Workshop Espresso"
    private val address = "East Building"
    private val email = "email@email.com"
    private val mobile = "0884808890"
    private val password = "a112233"
    private val confirmPassword = "a112233"

    @Test
    fun TC1001_RegisterSuccess() {
        Register.gotoRegisterScreenAndRegisterSuccess(
                "Workshop Espresso",
                "East Building",
                "email@email.com",
                "0884808890",
                "a112233",
                "a112233")
    }

    @Test
    fun TC1002_RegisterFailWithEmptyName() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "",
                "East Building",
                "email@email.com",
                "0884808890",
                "a112233",
                "a112233")
        Register.verifyNameError("at least 3 characters")
    }

    @Test
    fun TC1003_RegisterFailWithNameLessthan3Cheracters() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Wo",
                "East Building",
                "email@email.com",
                "0884808890",
                "a112233",
                "a112233")
        Register.verifyNameError("at least 3 characters")
    }

    @Test
    fun TC1004_RegisterFailWithEmptyAddress() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "",
                "email@email.com",
                "0884808890",
                "a112233",
                "a112233")
        Register.verifyAddressError("Enter Valid Address")
    }

    @Test
    fun TC1005_RegisterFailWithInvalidAddress() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "East Building",
                "email@email",
                "0884808890",
                "a112233",
                "a112233")
        Register.verifyEmailError("enter a valid email address")
    }

    @Test
    fun TC1006_RegisterFailWithEmptyEmail() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "East Building",
                "",
                "0884808890",
                "a112233",
                "a112233")
        Register.verifyEmailError("enter a valid email address")
    }

    @Test
    fun TC1007_RegisterFailWithEmptyMobileNumber() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "East Building",
                "email@email.com",
                "",
                "a112233",
                "a112233")
        Register.verifyMobileError("Enter Valid Mobile Number")
    }

    @Test
    fun TC1008_RegisterFailMobileNumberLessThan10() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "East Building",
                "email@email.com",
                "08848088",
                "a112233",
                "a112233")
        Register.verifyMobileError("Enter Valid Mobile Number")
    }

    @Test
    fun TC1009_RegisterFailMobileNumberMoreThan10() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "East Building",
                "email@email.com",
                "0884808890123",
                "a112233",
                "a112233")
        Register.verifyMobileError("Enter Valid Mobile Number")
    }

    @Test
    fun TC1010_RegisterFailPasswordLessThan4() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "East Building",
                "email@email.com",
                "0884808890",
                "a11",
                "a11")
        Register.verifyPasswordError("between 4 and 10 alphanumeric characters")
    }

    @Test
    fun TC1011_RegisterFailPasswordMoreThan10() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "East Building",
                "email@email.com",
                "0884808890",
                "a112233445566",
                "a112233445566")
        Register.verifyPasswordError("between 4 and 10 alphanumeric characters")
    }

    @Test
    fun TC1012_RegisterFailEmptyConfirmPasswordNotMatch() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "East Building",
                "email@email.com",
                "0884808890",
                "a112233",
                "")
        Register.verifyConfirmPasswordError("Password Do not match")
    }

    @Test
    fun TC1013_RegisterFailConfirmPasswordLessThan4() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "East Building",
                "email@email.com",
                "0884808890",
                "a112233",
                "a11")
        Register.verifyConfirmPasswordError("Password Do not match")
    }

    @Test
    fun TC1014_RegisterFailConfirmPasswordMoreThan10() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "East Building",
                "email@email.com",
                "0884808890",
                "a112233",
                "a112233445566")
        Register.verifyConfirmPasswordError("Password Do not match")
    }

    @Test
    fun TC1015_RegisterFailConfirmPasswordNotMatch() {
        Register.gotoRegisterScreen()
        Register.enterRegisterInfoAndSignUp(
                "Workshop Espresso",
                "East Building",
                "email@email.com",
                "0884808890",
                "a112233",
                "a1122")
        Register.verifyConfirmPasswordError("Password Do not match")
    }
}