package com.evarela.apirestlogin3.domain

import com.evarela.apirestlogin3.data.remote.Repository
import com.evarela.apirestlogin3.data.remote.model.LoginData
import com.evarela.apirestlogin3.domain.model.LoginModel
import com.evarela.apirestlogin3.domain.model.UserModel
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(data: LoginModel): String =
        repository.login(data)
}

