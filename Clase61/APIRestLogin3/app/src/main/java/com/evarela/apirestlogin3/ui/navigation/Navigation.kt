package com.evarela.apirestlogin3.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.evarela.apirestlogin3.ui.home.MainViewModel
import com.evarela.apirestlogin3.ui.home.MainScreen
import com.evarela.apirestlogin3.ui.login.LoginFormComponent


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Login.route
    ){
        composable(route = ScreenRoute.Login.route){
            LoginFormComponent(navController, viewModel)
        }
        composable(route = ScreenRoute.Main.route){
            MainScreen(viewModel)
        }
    }
}