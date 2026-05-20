package com.ccruz.taskending

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CredentialValidatorTest {

    @Test
    fun validateCredentials_whenUsernameIsEmpty_returnsUsernameRequiredError() {
        val result = validateCredentials(username = "", password = "123456")

        assertEquals("Username or email is required", result.usernameError)
        assertNull(result.passwordError)
    }

    @Test
    fun validateCredentials_whenUsernameIsBlank_returnsUsernameRequiredError() {
        val result = validateCredentials(username = "   ", password = "123456")

        assertEquals("Username or email is required", result.usernameError)
        assertNull(result.passwordError)
    }

    @Test
    fun validateCredentials_whenPasswordIsEmpty_returnsPasswordRequiredError() {
        val result = validateCredentials(username = "carlos", password = "")

        assertNull(result.usernameError)
        assertEquals("Password is required", result.passwordError)
    }

    @Test
    fun validateCredentials_whenUsernameIsInvalid_returnsInvalidUsernameError() {
        val result = validateCredentials(username = "carlos#", password = "12345")

        assertEquals("Enter a valid username or email", result.usernameError)
        assertNull(result.passwordError)
    }

    @Test
    fun validateCredentials_whenUsernameAndPasswordAreValid_returnsNoErrors() {
        val result = validateCredentials(username = "carlos", password = "123456")

        assertNull(result.usernameError)
        assertNull(result.passwordError)
        assertTrue(result.isValid)
    }

    @Test
    fun validateCredentials_whenUsernameHasSurroundingSpaces_isStillValid() {
        val result = validateCredentials(username = "  carlos  ", password = "123456")

        assertNull(result.usernameError)
        assertNull(result.passwordError)
        assertTrue(result.isValid)
    }

    @Test
    fun validateCredentials_whenEmailIsValid_returnsNoErrors() {
        val result = validateCredentials(username = "carlos@example.com", password = "123456")

        assertNull(result.usernameError)
        assertNull(result.passwordError)
        assertTrue(result.isValid)
    }
}
