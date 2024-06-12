package com.evarela.apirestlogin3.data.remote.model

import com.evarela.apirestlogin3.di.ApiConstants
import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName(value = ApiConstants.LOGIN_EMAIL_PARAM)
    var login: String = "eve.holt@reqres.in",

    @SerializedName(value = ApiConstants.LOGIN_PASSWORD_PARAM)
    var pwd: String = "cityslicka"
)
