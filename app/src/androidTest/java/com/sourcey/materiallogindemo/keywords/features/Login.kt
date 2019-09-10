package com.sourcey.materiallogindemo.keywords.features

import com.sourcey.materiallogindemo.keywords.screens.LoginScreen
import com.sourcey.materiallogindemo.keywords.screens.ProfileScreen

object Login {
    private val loginScreen by lazy { LoginScreen() }
    private val profileScreen by lazy { ProfileScreen() }

    fun enterInformationAndLogin(email: String, password: String) {
        loginScreen.iCanSeeLoginScreen()
        loginScreen.iEnterEmail(email)
        loginScreen.iEnterPassword(password)
        loginScreen.iClickBtnLogin()
    }

    fun verifyLoginFailedErrorMessage(emailError: String?, passwordError: String?) {
        loginScreen.iVerifyInputEmailErrorMessage(emailError)
        loginScreen.iVerifyInputPasswordErrorMessage(passwordError)
        loginScreen.iVerifyToastErrorMessage()
    }

    fun verifyLogInSuccess() {
        profileScreen.iVerifyProfileScreen()
    }

    fun enterInformationAndLoginWithHint(email: String, password: String) {
        loginScreen.iCanSeeLoginScreen()
        loginScreen.iEnterEmailByHint(email)
        loginScreen.iEnterPasswordByHint(password)
        loginScreen.iClickBtnLogin()
    }
}