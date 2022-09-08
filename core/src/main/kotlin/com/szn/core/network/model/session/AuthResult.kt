package com.szn.core.network.model.session

data class AuthResult(
    val expires_at: String?,
    val request_token: String?,
    val success: Boolean,
    val session_id: String?
)