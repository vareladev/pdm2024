package com.evarela.apirestlogin3.data.remote

import com.evarela.apirestlogin3.data.remote.api.ApiService
import com.evarela.apirestlogin3.data.remote.model.LoginData
import com.evarela.apirestlogin3.domain.model.LoginModel
import com.evarela.apirestlogin3.domain.model.UserModel
import retrofit2.HttpException
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiService,
){
    suspend fun login(data: LoginModel) : String {
        val loginData = LoginData(data.login, data.pwd)
        return api.login(loginData).token

    }

    suspend fun getUserList(page : Int): MutableList<UserModel>{
        val userListResponse = api.getUserList(page)
        val userModelList = mutableListOf<UserModel>()
        for (user in userListResponse.data){
            userModelList.add(
                UserModel(
                    id = user.id,
                    email = user.email,
                    firstName = user.firstName,
                    lastName = user.lastName,
                    profilePic = user.profilePic,
                )
            )
        }
        return userModelList
    }
}