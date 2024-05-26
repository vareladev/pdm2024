package com.evarela.apirestlogin.data.api

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName(value = "page")
    var page: Int,

    @SerializedName(value = "per_page")
    var per_page: Int,

    @SerializedName(value = "total")
    var total: Int,

    @SerializedName(value = "total_pages")
    var total_pages: Int,

    @SerializedName(value = "data")
    val data: ArrayList<UserData>,

)

data class UserData(
    @SerializedName(value = "id")
    var id: Int,

    @SerializedName(value = "email")
    val email: String,

    @SerializedName(value = "first_name")
    var firstName: String,

    @SerializedName(value = "last_name")
    var lastName: String,

    @SerializedName(value = "avatar")
    var profilePic: String,
)


