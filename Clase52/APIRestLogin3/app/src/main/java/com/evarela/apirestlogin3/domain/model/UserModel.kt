package com.evarela.apirestlogin3.domain.model


data class UserModel(
    var id: Int,

    val email: String,

    var firstName: String,

    var lastName: String,

    var profilePic: String,
)
