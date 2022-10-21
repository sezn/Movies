package com.szn.core.network.model.user

data class FavRequestBody(
    val favorite: Boolean,
    val media_id: Int,
    val media_type: String
)