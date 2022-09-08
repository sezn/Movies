package com.szn.core.extensions

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun String.toRequestBody(): RequestBody {
    return this.toRequestBody("application/json; charset=utf-8".toMediaType())
}