package com.evarela.ucalifes01.component.BottonNavBar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NavBarComponent(
    items: List<NavBarItem>,
    currentRoute: String?,
    onClick: (NavBarItem) -> Unit
){
    NavigationBar() {
        items.forEachIndexed { index, navBarItem ->
            NavigationBarItem(
                selected = currentRoute == navBarItem.route,
                onClick = { onClick(navBarItem) },
                icon = {
                    Icon(
                        imageVector =
                            if (currentRoute == navBarItem.route)
                                navBarItem.selectedIcon
                            else
                                navBarItem.unSelectedIcon,
                        contentDescription = navBarItem.title
                    )
                },
                label = {
                    Text(text = navBarItem.title)
                })
        }
    }

}