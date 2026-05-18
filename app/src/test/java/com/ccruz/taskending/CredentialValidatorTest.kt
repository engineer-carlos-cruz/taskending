package com.ccruz.taskending

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CredentialValidatorTest {

    @Test
    fun validateCredentials_whenUsernameIsEmpty_returnsUsernameRequiredError() {
        val result = validateCredentials(username = "", password = "123456")

        assertEquals("Username is required", result.usernameError)
        assertNull(result.passwordError)
    }

    @Test
    fun validateCredentials_whenUsernameIsBlank_returnsUsernameRequiredError() {
        val result = validateCredentials(username = "   ", password = "123456")

        assertEquals("Username is required", result.usernameError)
        assertNull(result.passwordError)
    }

    @Test
    fun validateCredentials_whenPasswordIsEmpty_returnsPasswordRequiredError() {
        val result = validateCredentials(username = "carlos", password = "")

        assertNull(result.usernameError)
        assertEquals("Password is required", result.passwordError)
    }

    @Test
    fun validateCredentials_whenPasswordIsTooShort_returnsMinLengthError() {
        val result = validateCredentials(username = "carlos", password = "12345")

        assertNull(result.usernameError)
        assertEquals("Password must be at least 6 characters", result.passwordError)
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
}
