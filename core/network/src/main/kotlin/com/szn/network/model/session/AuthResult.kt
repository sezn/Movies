package com.szn.network.model.session

import com.szn.network.model.ErrorResponse

data class AuthResult(
    val expires_at: String?,
    val request_token: String?,
    val success: Boolean,
    val session_id: String?,
    val error: ErrorResponse?
) {
    constructor(error: ErrorResponse) : this(null, null, false, null, error)
}