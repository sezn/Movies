package com.szn.movie.auth.network.model

data class UserSession(
    val username: String,
    val password: String,
    val request_token: String
)