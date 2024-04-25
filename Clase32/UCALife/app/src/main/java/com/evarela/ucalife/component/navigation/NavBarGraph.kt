package com.evarela.ucalife.component.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.evarela.ucalife.MainViewModel
import com.evarela.ucalife.screen.AddScreen
import com.evarela.ucalife.screen.HomeScreen
import com.evarela.ucalife.screen.ProfileScreen

@Composable
fun NavBarGraph(
    navController : NavHostController,
    innerPadding : PaddingValues,
    viewModel : MainViewModel
){
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Home.route
    ){
        composable(ScreenRoute.Home.route){
            HomeScreen(innerPadding, viewModel)
        }
        composable(ScreenRoute.Add.route){
            AddScreen(innerPadding)
        }
        composable(ScreenRoute.Profile.route){
           ProfileScreen(innerPadding)
        }
    }
}