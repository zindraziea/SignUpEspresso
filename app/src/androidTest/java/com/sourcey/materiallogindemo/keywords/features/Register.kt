package com.sourcey.materiallogindemo.keywords.features

import com.sourcey.materiallogindemo.keywords.screens.LoginScreen
import com.sourcey.materiallogindemo.keywords.screens.ProfileScreen
import com.sourcey.materiallogindemo.keywords.screens.RegisterScreen

object Register {
    private val loginScreen by lazy { LoginScreen() }
    private val registerScreen by lazy { RegisterScreen() }
    private val profileScreen by lazy { ProfileScreen() }

    fun gotoRegisterScreen() {
        loginScreen.iClickBtnRegister()
        registerScreen.iCanSeeRegisterScreen()
    }

    fun enterRegisterInfoAndSignUp(name: String, address: String, email: String, mobile: String, password: String, confirmPassword: String) {
        registerScreen.iEnterName(name)
        registerScreen.iEnterAddress(address)
        registerScreen.iEnterEmail(email)
        registerScreen.iEnterMobileNumber(mobile)
        registerScreen.iEnterPassword(password)
        registerScreen.iEnterConfirmPassword(confirmPassword)
        registerScreen.iClickBtnSignUp()
    }

    fun verifyRegisterSuccess() {
        profileScreen.iVerifyProfileScreen()
        profileScreen.iClickBtnLogOut()
    }

    fun gotoRegisterScreenAndRegisterSuccess(name: String, address: String, email: String, mobile: String, password: String, confirmPassword: String) {
        gotoRegisterScreen()
        enterRegisterInfoAndSignUp(name, address, email, mobile, password, confirmPassword)
        verifyRegisterSuccess()
    }

    fun verifyNameError(errorMessage: String?) {
        registerScreen.iVerifyNameError(errorMessage)
    }

    fun verifyAddressError(errorMessage: String?) {
        registerScreen.iVerifyAddressError(errorMessage)
    }

    fun verifyEmailError(errorMessage: String?) {
        registerScreen.iVerifyEmailError(errorMessage)
    }

    fun verifyMobileError(errorMessage: String?) {
        registerScreen.iVerifyMobileError(errorMessage)
    }

    fun verifyPasswordError(errorMessage: String?) {
        registerScreen.iVerifyPasswordError(errorMessage)
    }

    fun verifyConfirmPasswordError(errorMessage: String?) {
        registerScreen.iVerifyConfirmPasswordError(errorMessage)
    }
}