package com.evarela.ucalife.repository

interface DataStoreInterface {

    suspend fun saveRememberMe(value: Boolean)

    suspend fun getRememberMe() : Boolean
}