package com.evarela.ucalifes01.component.BottonNavBar

import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon : ImageVector
)
