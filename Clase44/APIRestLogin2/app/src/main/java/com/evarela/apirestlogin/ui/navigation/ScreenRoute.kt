package com.evarela.apirestlogin.ui.navigation

sealed class ScreenRoute(var route: String) {
    object  Login : ScreenRoute("login")
    object  Home : ScreenRoute("home")
}

