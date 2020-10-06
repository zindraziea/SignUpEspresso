package com.sourcey.materiallogindemo.keywords.feature

import com.sourcey.materiallogindemo.keywords.screen.LoginScreen

object Login {
    private val loginScreen = LoginScreen()

    fun gotoRegisterScreen() {
        loginScreen.iClickCreateNewAccount()
    }
}