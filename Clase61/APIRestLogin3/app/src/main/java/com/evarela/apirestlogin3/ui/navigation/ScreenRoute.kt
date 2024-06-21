package com.evarela.apirestlogin3.ui.navigation

sealed class ScreenRoute(var route: String) {
    data object  Login : ScreenRoute("login")
    data object  Main : ScreenRoute("main")
}

