package com.evarela.apirestlogin3.di

class ApiConstants {
    companion object {
        const val BASE_URL = "https://reqres.in"
        const val API_PATH = "/api"

        const val LOGIN_PATH = "/login"
        const val LOGIN_EMAIL_PARAM = "email"
        const val LOGIN_PASSWORD_PARAM = "password"
        const val LOGIN_RESPONSE_TOKEN = "token"
        const val LOGIN_RESPONSE_ERROR = "error"

        const val USERS_PATH = "/users"
    }
}