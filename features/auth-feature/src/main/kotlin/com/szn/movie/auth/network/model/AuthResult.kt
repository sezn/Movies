package com.szn.movie.auth.network.model

data class AuthResult(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)