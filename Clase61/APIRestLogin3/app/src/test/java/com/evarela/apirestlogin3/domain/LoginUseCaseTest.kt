package com.evarela.apirestlogin3.domain

import com.evarela.apirestlogin3.data.remote.Repository
import com.evarela.apirestlogin3.data.remote.model.LoginData
import com.evarela.apirestlogin3.data.remote.model.LoginResponse
import com.evarela.apirestlogin3.domain.model.LoginModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LoginUseCaseTest{

    // Dependency
    @RelaxedMockK
    private lateinit var repository: Repository

    // Tested class
    lateinit var loginUseCase : LoginUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        loginUseCase = LoginUseCase(repository)
    }

    @Test
    fun checkFormatOfReceivedToken(): Unit {
        runBlocking {
            //GIVEN
            coEvery {
                repository.login(
                    LoginModel()
                )
            } returns "QpwL5tke4Pnpja7X4"

            //WHEN
            val response =
                loginUseCase(
                    LoginModel()
                )

            //THEN
            assert(
                response.matches(Regex("([a-z0-9A-Z]{17})"))
            )
        }
    }

    @Test
    fun checkIncorrectLogin (): Unit = runBlocking {
        //GIVEN
        coEvery { repository.login(
            LoginModel()
        ) } returns "Missing password"

        //WHEN
        val response = loginUseCase(LoginModel())

        //THEN
        assert(response == "Missing password")
    }




}