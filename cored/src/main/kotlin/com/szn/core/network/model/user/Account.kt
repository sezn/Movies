package com.szn.core.network.model.user

import com.szn.core.BuildConfig
import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val avatar: Avatar?,
    val id: Int,
    val include_adult: Boolean = false,
    val iso_3166_1: String?,
    val iso_639_1: String?,
    val name: String?,
    val username: String?
) {
    constructor() : this(null, 0, false, null, null, null, null)

    fun getGravatar(): String {
        return BuildConfig.GRAVATAR_URL + avatar?.gravatar
    }
}