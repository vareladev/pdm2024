package com.evarela.ucalifes01.component.BottonNavBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.evarela.ucalifes01.screen.AddScreen
import com.evarela.ucalifes01.screen.HomeScreen
import com.evarela.ucalifes01.screen.ProfileScreen

@Composable
fun NavBarGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Home.route
    ) {
        composable(ScreenRoute.Home.route){
            HomeScreen(innerPadding)
        }
        composable(ScreenRoute.Add.route){
            AddScreen()
        }
        composable(ScreenRoute.Profile.route){
            ProfileScreen()
        }
    }

}