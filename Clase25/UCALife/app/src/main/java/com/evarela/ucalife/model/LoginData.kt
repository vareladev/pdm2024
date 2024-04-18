package com.evarela.ucalife.model

data class LoginData(
    var login: String = "",
    var pwd: String = "",
    var remember: Boolean = false
){
    fun isNotEmpty(): Boolean {
        return login.isNotEmpty() && pwd.isNotEmpty()
    }
}
