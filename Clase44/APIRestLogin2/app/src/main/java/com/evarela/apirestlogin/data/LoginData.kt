package com.evarela.apirestlogin.data

import com.evarela.apirestlogin.util.Constants
import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName(value = Constants.LOGIN_EMAIL_PARAM)
    var login: String = "eve.holt@reqres.in",

    @SerializedName(value = Constants.LOGIN_PASSWORD_PARAM)
    var pwd: String = "cityslicka"
)
