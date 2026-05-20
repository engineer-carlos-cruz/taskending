package com.ccruz.taskending

data class CredentialValidationResult(
    val usernameError: String? = null,
    val passwordError: String? = null
) {
    val isValid: Boolean
        get() = usernameError == null && passwordError == null
}

fun validateCredentials(username: String, password: String): CredentialValidationResult {
    val trimmedUsername = username.trim()
    val isUsernameEmpty = trimmedUsername.isEmpty()
    val isUsernameFormatInvalid = !isUsernameEmpty && !isValidUsernameOrEmail(trimmedUsername)
    val isPasswordEmpty = password.isEmpty()

    val usernameError = when {
        isUsernameEmpty -> "Username or email is required"
        isUsernameFormatInvalid -> "Enter a valid username or email"
        else -> null
    }
    val passwordError = when {
        isPasswordEmpty -> "Password is required"
        else -> null
    }

    return CredentialValidationResult(
        usernameError = usernameError,
        passwordError = passwordError
    )
}

private fun isValidUsernameOrEmail(value: String): Boolean {
    val usernamePattern = Regex("^[A-Za-z0-9_]{3,30}$")
    val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    return usernamePattern.matches(value) || emailPattern.matches(value)
}
