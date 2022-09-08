package com.szn.core.network.model

import retrofit2.HttpException

data class ErrorResponse(
    val status_code: Int,
    val status_message: String,
    val success: Boolean
): HttpException(
    null)