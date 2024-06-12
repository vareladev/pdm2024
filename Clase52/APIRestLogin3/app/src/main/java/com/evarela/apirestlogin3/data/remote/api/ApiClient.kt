package com.evarela.apirestlogin3.data.remote.api

import com.evarela.apirestlogin3.data.remote.model.LoginData
import com.evarela.apirestlogin3.data.remote.model.LoginResponse
import com.evarela.apirestlogin3.data.remote.model.UserListResponse
import com.evarela.apirestlogin3.di.ApiConstants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = ApiConstants.API_PATH + ApiConstants.LOGIN_PATH)
    suspend fun login(@Body data: LoginData) : LoginResponse

    @Headers(value = ["Content-Type: application/json"])
    @GET(value = ApiConstants.API_PATH + ApiConstants.USERS_PATH)
    suspend fun getUserList(@Query("page") page : Int): UserListResponse
}