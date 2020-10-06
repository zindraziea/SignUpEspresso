package com.sourcey.materiallogindemo.keywords.feature

import com.sourcey.materiallogindemo.keywords.screen.RegisterScreen

object Register {
    private val registerScreen = RegisterScreen()

    fun inputUserInfo(name: String, address: String, email: String, mobile: String, password: String, confirmPassword: String) {
        registerScreen.iInputName(name)
        registerScreen.iInputAddress(address)
        registerScreen.iInputEmail(email)
        registerScreen.iInputMobile(mobile)
        registerScreen.iInputPassword(password)
        registerScreen.iInputConfirmPassword(confirmPassword)
        registerScreen.iClickBtnCreateAccount()
    }

    fun inputUserInfo2(registerInfo: Map<String, String>) {
        registerScreen.apply {
            iInputName(registerInfo.getValue("name"))
            iInputAddress(registerInfo.getValue("address"))
            iInputEmail(registerInfo.getValue("email"))
            iInputMobile(registerInfo.getValue("mobile"))
            iInputPassword(registerInfo.getValue("password"))
            iInputConfirmPassword(registerInfo.getValue("confirmPassword"))
            iClickBtnCreateAccount()
        }
    }
}