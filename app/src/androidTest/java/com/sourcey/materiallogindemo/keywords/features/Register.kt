package com.sourcey.materiallogindemo.keywords.features

import com.sourcey.materiallogindemo.keywords.screens.RegisterInputScreen

class Register {
    //    private val loginFeature = Login()
//    private val registerFeature = Register()
//    private val manageProfileInfo = ManageProfileInfo()
    private val registerScreen = RegisterInputScreen()

    fun enterRegisterInfo(name: String, address: String, email: String, mobile: String, password: String, confirmPassword: String) {
//        registerScreen.iInputName(name)
//        registerScreen.iInputAddress(address)
        registerScreen.apply {
            iInputName(name)
            iInputAddress(address)
            iInputEmail(email)
            iInputMobile(mobile)
            iInputPassword(password)
            iInputConfirmPassword(confirmPassword)
            iClickSubmit()
        }
    }

    fun registerSuccess(name: String, address: String, email: String, mobile: String, password: String, confirmPassword: String) {
        Login().gotoRegisterInputScreen()
        Register().enterRegisterInfo(name, address, email, mobile, password, confirmPassword)
        ManageProfileInfo().verifyProfileInfo(name, address, email, mobile)
    }

    fun registerSuccess2(userInfo: Map<String, String>) {
        Login().gotoRegisterInputScreen()
        Register().enterRegisterInfo(
                userInfo.getValue("name"),
                userInfo.getValue("address"),
                userInfo.getValue("email"),
                userInfo.getValue("mobile"),
                userInfo.getValue("password"),
                userInfo.getValue("confirmPassword"))
        ManageProfileInfo().verifyProfileInfo(
                userInfo.getValue("name"), userInfo.getValue("address"), userInfo.getValue("email"), userInfo.getValue("mobile"))
    }

    fun verifyMobileError() {
        registerScreen.iVerifyMobileError()
    }
}