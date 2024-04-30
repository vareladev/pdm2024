package com.evarela.ucalife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.evarela.ucalife.component.navigation.BottomNavigationBar
import com.evarela.ucalife.component.navigation.NavBarGraph
import com.evarela.ucalife.component.navigation.NavBarItemList
import com.evarela.ucalife.ui.theme.UCALifeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        installSplashScreen()
        setContent {
            UCALifeTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute: String? = navBackStackEntry?.destination?.route
                val navItems = NavBarItemList()

                Scaffold (
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = Color.White,
                            ),
                            title = {
                                Text(stringResource(R.string.app_name))
                            }
                        )
                    },
                    bottomBar = {
                        BottomNavigationBar(items = navItems, currentRoute = currentRoute) {
                            currentNavigationItem ->
                            navController.navigate(currentNavigationItem.route){
                                navController.graph.startDestinationRoute?.let{startDestinationRoute ->
                                    popUpTo(startDestinationRoute){
                                        saveState = true
                                    }
                                }
                                launchSingleTop=true
                                restoreState = true
                            }
                        }
                    }
                ){
                        innerPadding ->
                    NavBarGraph(
                        navController = navController,
                        innerPadding = innerPadding,
                        viewModel = mainViewModel
                    )
                }
            }
        }
    }
}
