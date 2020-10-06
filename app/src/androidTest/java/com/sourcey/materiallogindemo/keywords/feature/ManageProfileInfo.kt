package com.sourcey.materiallogindemo.keywords.feature

import com.sourcey.materiallogindemo.keywords.screen.ProfileScreen
import com.sourcey.materiallogindemo.keywords.screen.UpdateProfileScreen

object ManageProfileInfo {
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

    fun gotoUpdateProfileScreen() {
        profileScreen.apply {
            iClickMoreOption()
            iClickUpdateProfile()
        }
        updateProfileScreen.iCanSeeUpdateProfileScreen()
    }

    fun enterProfileInfo(userInfo: Map<String, String>) {
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

    fun clickSaveUpdateProfile() {
        updateProfileScreen.iClickSave()
    }
}