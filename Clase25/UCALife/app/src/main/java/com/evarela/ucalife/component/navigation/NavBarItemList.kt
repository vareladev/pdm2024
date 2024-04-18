package com.evarela.ucalife.component.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.evarela.ucalife.R

@Composable
fun NavBarItemList(): List<NavBarItem> {
    return listOf(
        NavBarItem(
            stringResource(id = R.string.navbar_home),
            ScreenRoute.Home.route,
            ImageVector.vectorResource(id = R.drawable.ic_home_filled),
            ImageVector.vectorResource(id = R.drawable.ic_home_outlined)
        ),
        NavBarItem(
            stringResource(id = R.string.navbar_add),
            ScreenRoute.Add.route,
            ImageVector.vectorResource(id = R.drawable.ic_add_filled),
            ImageVector.vectorResource(id = R.drawable.ic_add_outlined)
        ),
        NavBarItem(
            stringResource(id = R.string.navbar_profile),
            ScreenRoute.Profile.route,
            ImageVector.vectorResource(id = R.drawable.ic_profile_filled),
            ImageVector.vectorResource(id = R.drawable.ic_profile_outlined)
        )
    )
}