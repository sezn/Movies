package com.szn.core.network.model.user

import kotlinx.serialization.Serializable

@Serializable
data class Avatar(
    val gravatar: Gravatar,
    val tmdb: Tmdb
)

@Serializable
data class Gravatar(
    val hash: String
)

@Serializable
data class Tmdb(
    val avatar_path: String?
)