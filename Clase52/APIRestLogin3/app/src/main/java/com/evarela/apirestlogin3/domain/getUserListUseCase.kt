package com.evarela.apirestlogin3.domain

import com.evarela.apirestlogin3.data.remote.Repository
import com.evarela.apirestlogin3.data.remote.model.UserListResponse
import com.evarela.apirestlogin3.domain.model.UserModel
import javax.inject.Inject

class getUserListUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(page : Int): MutableList<UserModel> =
        repository.getUserList(page)
}