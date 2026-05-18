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
    val isPasswordEmpty = password.isEmpty()
    val isPasswordTooShort = !isPasswordEmpty && password.length < 6

    val usernameError = if (isUsernameEmpty) "Username is required" else null
    val passwordError = when {
        isPasswordEmpty -> "Password is required"
        isPasswordTooShort -> "Password must be at least 6 characters"
        else -> null
    }

    return CredentialValidationResult(
        usernameError = usernameError,
        passwordError = passwordError
    )
}
