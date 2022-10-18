package com.szn.movie.auth

import com.szn.movie.auth.network.model.AuthResult

interface UserRepository {

    suspend fun newToken(): AuthResult

    suspend fun createSessionId(): AuthResult

    suspend fun login(mail: String, pass: String): AuthResult
}