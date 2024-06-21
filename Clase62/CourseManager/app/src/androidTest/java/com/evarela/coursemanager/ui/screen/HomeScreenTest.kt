package com.evarela.coursemanager.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.evarela.coursemanager.MainViewModel
import com.evarela.coursemanager.ui.navigation.Navigation
import com.evarela.coursemanager.ui.navigation.ScreenRoute
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest(){
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var manViewModel : MainViewModel
    private lateinit var navController : TestNavHostController

    @Before
    fun onBefore(){
        manViewModel = MainViewModel()

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }

            Navigation(manViewModel, navController)
        }
    }

    // Checking navigation default route
    @Test
    fun testNavigationHostVerifyStartDestination(){
        assertEquals(ScreenRoute.Home.route, navController.currentBackStackEntry?.destination?.route)
    }

    // Home Screen Content Test
    @Test
    fun testCheckAvailableListOfCoursesInHomeScreen(){
        composeTestRule.onNodeWithText("PDM - Programación de dispositivo móviles").assertExists()
    }

    // Home Screen navigations tests
    @Test
    fun testNavigationFromHomeScreenToAddScreen(){
        composeTestRule.apply {
            onNodeWithTag("Create course").performClick()
            waitForIdle()
            val route = navController.currentBackStackEntry?.destination?.route
            if (route != null){
                assertEquals(ScreenRoute.Add.route, route)
            }
        }
    }

    @Test
    fun testNavigationFromHomeScreenToEditScreen(){
        composeTestRule.apply{
            onNodeWithText("PDM - Programación de dispositivo móviles").performClick()
            waitForIdle()
            onNodeWithText("Edit course")
        }
    }

    // Topbar behavior tests
    @Test
    fun testVerifyBackNavigationButtonNotShownInHomeScreen(){
        composeTestRule.onNodeWithContentDescription("Back").assertDoesNotExist()
    }

    @Test
    fun testVerifyDeleteButtonNotShownInHomeScreen(){
        composeTestRule.onNodeWithContentDescription("Delete").assertDoesNotExist()
    }

    @Test
    fun testVerifySaveButtonNotShownInHomeScreen(){
        composeTestRule.onNodeWithContentDescription("Save").assertDoesNotExist()
    }

}