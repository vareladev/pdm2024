package com.evarela.apirestlogin3.data.remote.api

import com.evarela.apirestlogin3.data.remote.model.LoginData
import com.evarela.apirestlogin3.data.remote.model.LoginResponse
import com.evarela.apirestlogin3.data.remote.model.UserListResponse
import retrofit2.http.Body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query
import javax.inject.Inject

class ApiService  @Inject constructor(
    private val api: ApiClient
){
    suspend fun login(data: LoginData) : LoginResponse {
        return withContext(Dispatchers.IO) {
            api.login(data)
        }
    }

    suspend fun getUserList(page : Int): UserListResponse {
        return withContext(Dispatchers.IO) {
            api.getUserList(page)
        }
    }
}