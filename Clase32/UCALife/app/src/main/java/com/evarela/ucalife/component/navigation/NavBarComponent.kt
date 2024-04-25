package com.evarela.ucalife.component.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.evarela.ucalife.ui.theme.Gray
import com.evarela.ucalife.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<NavBarItem>,
    currentRoute: String?,
    onClick: (NavBarItem) -> Unit
){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ){
        items.forEachIndexed { index, navBarItem ->
            NavigationBarItem(
                selected = currentRoute == navBarItem.route,
                onClick = {onClick(navBarItem)},
                icon = {
                    Icon(
                        imageVector = if (currentRoute == navBarItem.route){
                            navBarItem.selectedIcon
                        } else {
                            navBarItem.unSelectedIcon
                        },
                        contentDescription = navBarItem.title,
                    )
                },
                label = {
                    Text(text = navBarItem.title, fontSize= 10.sp)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor =
                    if(isSystemInDarkTheme())
                        Gray
                    else
                        LightGray
                )
            )
        }
    }
}