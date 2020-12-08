package com.sourcey.materiallogindemo.keyword.feature

import com.sourcey.materiallogindemo.keyword.screen.LoginScreen
import com.sourcey.materiallogindemo.keyword.screen.SignUpScreen
import com.sourcey.materiallogindemo.keyword.screen.WelcomeScreen

object SignupFeature {

    val loginScreen = LoginScreen()
    val signUpScreen = SignUpScreen()
    val welcomeScreen = WelcomeScreen()

    fun iSignUpNewUser(name: String, address: String, email: String, mobile: String, password: String, confirmPass: String) {
        loginScreen.iClickSignUp()
        signUpScreen.iInputName(name)
        signUpScreen.iInputAddress(address)
        signUpScreen.iInputEmail(email)
        signUpScreen.iInputMobile(mobile)
        signUpScreen.iInputPassword(password)
        signUpScreen.iInputConfirmPassword(confirmPass)
        signUpScreen.iClickSubmit()
    }

    fun iVerifyNameError() {
        signUpScreen.iVerifyThoseError()
        signUpScreen.iVerifyErrorName()
    }

    fun iVerifyAddressError() {
        signUpScreen.iVerifyThoseError()
        signUpScreen.iVerifyErrorAddress()
    }

    fun iVerifyEmailError() {
        signUpScreen.iVerifyThoseError()
        signUpScreen.iVerifyErrorEmail()
    }

    fun iVerifyMobileError() {
        signUpScreen.iVerifyThoseError()
        signUpScreen.iVerifyErrorMobile()
    }

    fun iVerifyPasswordError() {
        signUpScreen.iVerifyThoseError()
        signUpScreen.iVerifyErrorPassword()
    }

    fun iVerifyComfirmPasswordError() {
        signUpScreen.iVerifyThoseError()
        signUpScreen.iVerifyErrorComfirmPassword()
    }

    fun iVerifyLoginSuccress(name: String, email: String, address: String, mobile: String) {
        welcomeScreen.iVerifyWelcomeText()
        welcomeScreen.iVerifyName(name)
        welcomeScreen.iVerifyEmail(email)
        welcomeScreen.iVerifyAddress(address)
        welcomeScreen.iVerifyMobile(mobile)
        welcomeScreen.iClickLogout()
        welcomeScreen.iClickLogoutOk()
        loginScreen.iVerifySignUpIsDisplay()
    }

}