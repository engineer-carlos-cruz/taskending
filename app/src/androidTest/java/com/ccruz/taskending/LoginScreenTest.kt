package com.ccruz.taskending

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun signIn_withEmptyPassword_showsValidationError() {
        composeTestRule.onNodeWithText("Username or email").performTextInput("carlos")
        composeTestRule.onNodeWithText("Sign in").performClick()

        composeTestRule.onNodeWithText("Password is required").assertIsDisplayed()
    }

    @Test
    fun signIn_withValidCredentials_navigatesToMainScreen() {
        composeTestRule.onNodeWithText("Username or email").performTextInput("carlos")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Sign in").performClick()

        composeTestRule.onNodeWithText("Hello Android!").assertIsDisplayed()
    }
}
