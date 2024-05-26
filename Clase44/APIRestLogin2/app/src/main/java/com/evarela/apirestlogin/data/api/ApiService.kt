package com.evarela.apirestlogin.data.api

import com.evarela.apirestlogin.data.LoginData
import com.evarela.apirestlogin.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.LOGIN_PATH)
    suspend fun login(@Body data: LoginData) : LoginResponse

    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.USERS_PATH)
    suspend fun getUserList(@Query("page") page : Int): UserListResponse

}





