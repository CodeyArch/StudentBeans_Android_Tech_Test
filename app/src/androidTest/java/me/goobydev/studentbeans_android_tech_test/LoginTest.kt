package me.goobydev.studentbeans_android_tech_test

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.goobydev.studentbeans_android_tech_test.feature_login_screen.presentation.LoginScreen
import org.junit.Rule
import org.junit.Test

class LoginTest {
    @get:Rule
    val rule = createComposeRule()


    // Test that email and password are not empty and log in would work
    @Test
    fun validEmailAndPasswordTest() {
        rule.setContent {
            LoginScreen(
                navController = rememberNavController(),
                isTest = true // Stops NPEs related to navigation and testing
            )
        }
        rule.onNode(
            hasContentDescription("Email") and hasSetTextAction()
        ).performTextInput("TotallyValidEmail@email.com")
        rule.onNode(
            hasContentDescription("Password") and hasSetTextAction()
        ).performTextInput("password")
        rule.onNode(
            hasText("Log in") and hasClickAction()
        ).performClick()
        rule.onNodeWithText(text = "Success, this would be a navigation").assertIsDisplayed()
    }

    // Test that error occurs if only email is empty
    @Test
    fun invalidEmailTest() {
        rule.setContent {
            LoginScreen(
                navController = rememberNavController(),
                isTest = true // Stops NPEs related to navigation and testing
            )
        }
        rule.onNode(
            hasContentDescription("Email") and hasSetTextAction()
        ).assertIsDisplayed()
        rule.onNode(
            hasContentDescription("Password") and hasSetTextAction()
        ).performTextInput("password")
        rule.onNode(
            hasText("Log in") and hasClickAction()
        ).performClick()
        rule.onNodeWithText(text = "Username and/or Password cannot be empty").assertIsDisplayed()

    }

    // Test that error occurs if only password is empty
    @Test
    fun invalidPasswordTest() {
        rule.setContent {
            LoginScreen(
                navController = rememberNavController(),
                isTest = true // Stops NPEs related to navigation and testing
            )
        }
        rule.onNode(
            hasContentDescription("Email") and hasSetTextAction()
        ).performTextInput("TotallyValidEmail@email.com")
        rule.onNode(
            hasContentDescription("Password") and hasSetTextAction()
        ).assertIsDisplayed()
        rule.onNode(
            hasText("Log in") and hasClickAction()
        ).performClick()
        rule.onNodeWithText(text = "Username and/or Password cannot be empty").assertIsDisplayed()
    }

    // Test that error occurs if both email and password are empty
    @Test
    fun invalidEmailAndPasswordTest() {
        rule.setContent {
            LoginScreen(
                navController = rememberNavController(),
                isTest = true // Stops NPEs related to navigation and testing
            )
        }
        rule.onNode(
            hasContentDescription("Email") and hasSetTextAction()
        ).assertIsDisplayed()
        rule.onNode(
            hasContentDescription("Password") and hasSetTextAction()
        ).assertIsDisplayed()
        rule.onNode(
            hasText("Log in") and hasClickAction()
        ).performClick()
        rule.onNodeWithText(text = "Success, this would be a navigation").assertDoesNotExist()
        rule.onNodeWithText(text = "Username and/or Password cannot be empty").assertIsDisplayed()
    }
}