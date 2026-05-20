package com.ccruz.taskending

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val usernameError: String? = null,
    val passwordError: String? = null,
    val authError: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)

class LoginViewModel(
    private val authRepository: AuthRepository = DefaultAuthRepository()
) : ViewModel() {
    var state by mutableStateOf(LoginUiState())
        private set

    fun onUsernameChange(value: String) {
        state = state.copy(username = value, usernameError = null, authError = null)
    }

    fun onPasswordChange(value: String) {
        state = state.copy(password = value, passwordError = null, authError = null)
    }

    fun onSignInClick() {
        if (state.isLoading) return

        val validation = validateCredentials(state.username, state.password)
        if (!validation.isValid) {
            state = state.copy(
                usernameError = validation.usernameError,
                passwordError = validation.passwordError,
                authError = null,
                isSuccess = false
            )
            return
        }

        state = state.copy(isLoading = true, authError = null, isSuccess = false)
        val authenticated = authRepository.signIn(state.username.trim(), state.password)
        state = state.copy(
            isLoading = false,
            authError = if (authenticated) null else "Authentication failed",
            isSuccess = authenticated
        )
    }
}
