package com.szn.core.network.model.user

import com.szn.core.network.model.Gravatar

data class Avatar(
    val gravatar: Gravatar,
    val tmdb: Tmdb
)