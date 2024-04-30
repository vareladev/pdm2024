package com.evarela.ucalife.util

import android.content.Context
import com.evarela.ucalife.R

fun errorList(context: Context, code: Int): String? {
    val ErrorList = mapOf<Int, String>(
        1 to context.resources.getString(R.string.error_1),
        2 to context.resources.getString(R.string.error_2)
    )
    return ErrorList[code]
}


