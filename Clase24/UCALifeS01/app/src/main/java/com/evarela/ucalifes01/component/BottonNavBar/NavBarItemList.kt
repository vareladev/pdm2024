package com.evarela.ucalifes01.component.BottonNavBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.evarela.ucalifes01.R

@Composable
fun NavBarItemList(): List<NavBarItem>{
    return listOf(
        NavBarItem(
            "Home",
            ScreenRoute.Home.route,
            ImageVector.vectorResource(id = R.drawable.ic_home_filled),
            ImageVector.vectorResource(id = R.drawable.ic_home_outlined)
        ),
        NavBarItem(
            "Add",
            ScreenRoute.Add.route,
            ImageVector.vectorResource(id = R.drawable.ic_add_filled),
            ImageVector.vectorResource(id = R.drawable.ic_add_outlined)
        ),
        NavBarItem(
            "Profile",
            ScreenRoute.Profile.route,
            ImageVector.vectorResource(id = R.drawable.ic_setting_filled),
            ImageVector.vectorResource(id = R.drawable.ic_setting_outlined)
        )
    )
}
