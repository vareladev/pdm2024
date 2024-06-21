package com.evarela.apirestlogin3.data.remote.model

import com.evarela.apirestlogin3.di.ApiConstants
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName(value = ApiConstants.LOGIN_RESPONSE_TOKEN)
    var token: String
)
