package com.evarela.ucalifes01.component.BottonNavBar

sealed class ScreenRoute (var route: String) {
    object Home : ScreenRoute("home")
    object Add : ScreenRoute("add")
    object Profile : ScreenRoute ("profile")
}