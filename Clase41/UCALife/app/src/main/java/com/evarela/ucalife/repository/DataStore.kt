package com.evarela.ucalife.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

val data_store_name = "UCALIFEPREFERENCES"
val Context.datastore : DataStore<Preferences>
        by preferencesDataStore(name = data_store_name)





class DataStore (private val context: Context){

    val remember_user_key = booleanPreferencesKey("remember_user")

    suspend fun saveRememberMe(value: Boolean){
        context.datastore.edit { datastore ->
            datastore[remember_user_key] = value
        }
    }


    fun getRememberMe() = context.datastore.data.map {
        datastore ->
            datastore[remember_user_key]
    }


}