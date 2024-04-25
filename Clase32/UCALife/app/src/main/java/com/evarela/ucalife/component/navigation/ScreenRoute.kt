package com.evarela.ucalife.component.navigation

sealed class ScreenRoute(var route: String) {
    object  Home : ScreenRoute("home")
    object  Add : ScreenRoute("add")
    object  Profile : ScreenRoute("profile")
}

