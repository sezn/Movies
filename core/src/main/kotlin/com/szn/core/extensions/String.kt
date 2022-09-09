package com.szn.core.extensions

import com.google.gson.Gson
import com.szn.core.network.model.ErrorResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun String.toRequestBody(): RequestBody {
    return this.toRequestBody("application/json; charset=utf-8".toMediaType())
}

fun String.fromJson(cl: Class<ErrorResponse>): Any {
    return Gson().fromJson(this, cl)
}

inline fun <reified T> String.fromJson(json: String) : T = Gson().fromJson<T>(json, T::class.java)