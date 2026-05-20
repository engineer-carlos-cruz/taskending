package com.ccruz.taskending

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginViewModelTest {

    @Test
    fun onSignInClick_withInvalidInput_setsValidationErrors() {
        val viewModel = LoginViewModel(authRepository = FakeAuthRepository(true))

        viewModel.onUsernameChange("bad#name")
        viewModel.onPasswordChange("")
        viewModel.onSignInClick()

        assertEquals("Enter a valid username or email", viewModel.state.usernameError)
        assertEquals("Password is required", viewModel.state.passwordError)
        assertFalse(viewModel.state.isSuccess)
    }

    @Test
    fun onSignInClick_withAuthFailure_setsAuthError() {
        val viewModel = LoginViewModel(authRepository = FakeAuthRepository(false))

        viewModel.onUsernameChange("carlos")
        viewModel.onPasswordChange("password123")
        viewModel.onSignInClick()

        assertEquals("Authentication failed", viewModel.state.authError)
        assertFalse(viewModel.state.isSuccess)
        assertFalse(viewModel.state.isLoading)
    }

    @Test
    fun onSignInClick_withAuthSuccess_setsSuccess() {
        val viewModel = LoginViewModel(authRepository = FakeAuthRepository(true))

        viewModel.onUsernameChange("carlos")
        viewModel.onPasswordChange("password123")
        viewModel.onSignInClick()

        assertTrue(viewModel.state.isSuccess)
        assertFalse(viewModel.state.isLoading)
    }
}

private class FakeAuthRepository(private val result: Boolean) : AuthRepository {
    override fun signIn(username: String, password: String): Boolean = result
}
