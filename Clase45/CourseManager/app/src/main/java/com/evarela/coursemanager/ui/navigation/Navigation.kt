package com.evarela.coursemanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.evarela.coursemanager.MainViewModel
import com.evarela.coursemanager.ui.screen.AddScreen
import com.evarela.coursemanager.ui.screen.EditScreen
import com.evarela.coursemanager.ui.screen.HomeScreen

@Composable
fun Navigation(
    viewModel : MainViewModel
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Home.route
    ){
        composable(route = ScreenRoute.Home.route){
            HomeScreen(viewModel, navController)
        }
        composable(route = ScreenRoute.Add.route){
            AddScreen(viewModel, navController)
        }
        composable(
            route = "${ScreenRoute.Edit.route}/{code}",
            arguments = listOf(
                navArgument("code"){
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            EditScreen(viewModel, navController, backStackEntry.arguments?.getString("code"))
        }
    }
}