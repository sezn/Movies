package com.szn.network.model.session

data class UserSession(
    val username: String?,
    val password: String?,
    val request_token: String
) {
    constructor(token: String): this(null, null, token)
}