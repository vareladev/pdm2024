package com.evarela.ucalife.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.evarela.ucalife.LoginState

fun changeActivity(context: Context, activity: Class<*>){
    context.startActivity(Intent(context, activity))
    (context as Activity).finish()
}

fun showMessage(context: Context, errorCode: Int){
    Toast.makeText(context,
        errorList(context, errorCode),
        Toast.LENGTH_SHORT).show()
}