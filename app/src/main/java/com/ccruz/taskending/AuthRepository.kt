package com.ccruz.taskending

interface AuthRepository {
    fun signIn(username: String, password: String): Boolean
}

class DefaultAuthRepository : AuthRepository {
    override fun signIn(username: String, password: String): Boolean {
        return username.trim().isNotEmpty() && password == "password123"
    }
}
