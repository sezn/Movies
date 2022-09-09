package com.szn.core.network.model.user

data class Account(
    val avatar: Avatar,
    val id: Int,
    val include_adult: Boolean,
    val iso_3166_1: String,
    val iso_639_1: String,
    val name: String,
    val username: String
)