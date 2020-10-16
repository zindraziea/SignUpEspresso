package com.sourcey.materiallogindemo.keywords.features

import com.sourcey.materiallogindemo.keywords.screens.ProfileScreen
import com.sourcey.materiallogindemo.keywords.screens.UpdateProfileScreen

class ManageProfileInfo {
    private val profileScreen = ProfileScreen()
    private val updateProfileScreen = UpdateProfileScreen()

    fun verifyProfileInfo(name: String, address: String, email: String, mobile: String) {
        profileScreen.apply {
            iCanSeeProfileScreen()
            iVerifyName(name)
            iVerifyAddress(address)
            iVerifyEmail(email)
            iVerifyMobile(mobile)
        }
    }

    fun gotoUpdateProfileScreen() {
        profileScreen.apply {
            iClickMoreOption()
            iClickUpdateProfile()
        }
        updateProfileScreen.iCanSeeUpdateProfileScreen()
    }

    fun enterProfileInfo(name: String = "", address: String, email: String, mobile: String) {
        updateProfileScreen.iInputName(name)
        updateProfileScreen.iInputEmail(address)
        updateProfileScreen.iInputAddress(email)
        updateProfileScreen.iInputMobile(mobile)
    }

    fun clickSaveUpdateProfile() {
        updateProfileScreen.iClickSave()
    }

    fun enterProfileInfo2(userInfo: Map<String, String>) {
        if (userInfo.containsKey("name")) {
            updateProfileScreen.iInputName(userInfo.getValue("name"))
        }

        if (userInfo.containsKey("email")) {
            updateProfileScreen.iInputEmail(userInfo.getValue("email"))
        }

        if (userInfo.containsKey("address")) {
            updateProfileScreen.iInputAddress(userInfo.getValue("address"))
        }

        if (userInfo.containsKey("mobile")) {
            updateProfileScreen.iInputMobile(userInfo.getValue("mobile"))
        }
    }

    fun verifyProfileInfo2(userInfo: Map<String, String>) {
        profileScreen.iCanSeeProfileScreen()

        if (userInfo.containsKey("name")) {
            profileScreen.iVerifyName(userInfo.getValue("name"))
        }

        if (userInfo.containsKey("email")) {
            profileScreen.iVerifyEmail(userInfo.getValue("email"))
        }

        if (userInfo.containsKey("address")) {
            profileScreen.iVerifyAddress(userInfo.getValue("address"))
        }

        if (userInfo.containsKey("mobile")) {
            profileScreen.iVerifyMobile(userInfo.getValue("mobile"))
        }
    }
}