package com.evarela.apirestlogin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.evarela.apirestlogin.MainViewModel
import com.evarela.apirestlogin.ui.component.LoginFormComponent
import com.evarela.apirestlogin.ui.screen.HomeScreen

@Composable
fun Navigation(
    viewModel: MainViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Login.route
    ){
        composable(route = ScreenRoute.Login.route){
            LoginFormComponent(navController, viewModel)
        }
        composable(route = ScreenRoute.Home.route){
            HomeScreen(navController, viewModel)
        }
    }
}