package com.evarela.apirestlogin3.domain

import com.evarela.apirestlogin3.data.remote.Repository
import com.evarela.apirestlogin3.domain.model.LoginModel
import com.evarela.apirestlogin3.domain.model.UserModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class getUserListUseCaseTest{

    // Dependency
    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var userListUserCase : getUserListUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        userListUserCase = getUserListUseCase(repository)
    }

    @Test
    fun userListIsEmpty(): Unit = runBlocking {
        //GIVEN
        coEvery { repository.getUserList(2) } returns ArrayList()

        //WHEN
        val result = userListUserCase(2)

        //THEN
        assert(result.isEmpty())
    }

    @Test
    fun userListIsNotEmpty(): Unit = runBlocking{
        //GIVEN
        val userList = mutableListOf<UserModel>()
        userList.add(
            UserModel(
                0,"","","","",
            )
        )
        coEvery {
            repository.getUserList(2)
        } returns userList

        //WHEN
        val result = userListUserCase(2)

        //THEN
        assert(result.isNotEmpty())
    }
}