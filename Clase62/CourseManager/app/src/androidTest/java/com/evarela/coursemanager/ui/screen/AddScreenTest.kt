package com.evarela.coursemanager.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
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
class AddScreenTest(){
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

    // Topbar behavior tests
    @Test
    fun testVerifyBackNavigationButtonNotShownInHomeScreen(){
        composeTestRule.apply {
            onNodeWithTag("Create course").performClick()
            waitForIdle()
            onNodeWithContentDescription("Back").assertExists()
        }
    }

    @Test
    fun testVerifyDeleteButtonNotShownInHomeScreen(){
        composeTestRule.apply {
            onNodeWithTag("Create course").performClick()
            waitForIdle()
            onNodeWithContentDescription("Delete").assertDoesNotExist()
        }
    }

    @Test
    fun testVerifySaveButtonNotShownInHomeScreen(){
        composeTestRule.apply {
            onNodeWithTag("Create course").performClick()
            waitForIdle()
            onNodeWithContentDescription("Save").assertExists()
        }
    }

    // Back button functionality test
    @Test
    fun TestBackButtonFunctionality(){
        composeTestRule.apply {
            onNodeWithTag("Create course").performClick()
            waitForIdle()
            onNodeWithContentDescription("Back").performClick()
            waitForIdle()
            val route = navController.currentBackStackEntry?.destination?.route
            assertEquals(ScreenRoute.Home.route, route)
        }
    }


    // Adding new course test
    @Test
    fun testAddingNewCourseUsingFormShownInAddScreen(){
        composeTestRule.apply {
            onNodeWithTag("Create course").performClick()
            waitForIdle()
            onNodeWithTag("Code").performTextInput("PDM")
            onNodeWithTag("Title").performTextInput("Programacion de dispositivos moviles")
            onNodeWithTag("Description").performTextInput("Curso")
            onNodeWithTag("Category").performTextInput("Desarrollo")
            onNodeWithTag("Topic").performTextInput("Fundamentos Android")
            onNodeWithTag("Add topic").performClick()
            onNodeWithContentDescription("Save").performClick()
            waitForIdle()
            val route = navController.currentBackStackEntry?.destination?.route
            assertEquals(ScreenRoute.Home.route, route)
        }
    }
}