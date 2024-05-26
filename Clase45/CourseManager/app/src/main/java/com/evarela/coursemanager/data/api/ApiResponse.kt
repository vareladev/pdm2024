package com.evarela.coursemanager.data.api

import com.evarela.coursemanager.utils.Constants
import com.google.gson.annotations.SerializedName

data class ApiResponseSuccessful(
    @SerializedName(value = Constants.RESPONSE_SUCCESSFUL)
    val result : String
)

data class ApiResponseError(
    @SerializedName(value = Constants.RESPONSE_ERROR)
    val message : String
)
