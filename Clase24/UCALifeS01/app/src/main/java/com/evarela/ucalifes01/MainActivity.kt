package com.evarela.ucalifes01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.evarela.ucalifes01.component.BottonNavBar.NavBarComponent
import com.evarela.ucalifes01.component.BottonNavBar.NavBarGraph
import com.evarela.ucalifes01.component.BottonNavBar.NavBarItemList
import com.evarela.ucalifes01.screen.HomeScreen
import com.evarela.ucalifes01.ui.theme.BackgroundGray
import com.evarela.ucalifes01.ui.theme.InstitutionalBlue
import com.evarela.ucalifes01.ui.theme.UCALifeS01Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UCALifeS01Theme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute : String? = navBackStackEntry?.destination?.route
                val navItems = NavBarItemList()

                Scaffold (
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = InstitutionalBlue,
                                titleContentColor = BackgroundGray,
                            ),
                            title = {
                                Text(stringResource(id = R.string.app_name))
                            }
                        )
                    },
                    bottomBar = {
                        NavBarComponent(
                            items = navItems,
                            currentRoute = currentRoute) {
                            currentNavigationItem ->
                            navController.navigate(currentNavigationItem.route){
                                navController.graph.startDestinationRoute?.let{
                                    startDestinationRoute ->
                                    popUpTo(startDestinationRoute){
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                ){
                    innerPadding -> NavBarGraph(
                    navController = navController,
                    innerPadding = innerPadding)
                }

            }
        }
    }
}
