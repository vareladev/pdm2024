package com.evarela.apirestlogin.data.api

import com.evarela.apirestlogin.util.Constants
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName(value = Constants.LOGIN_RESPONSE_TOKEN)
    var token: String
)
