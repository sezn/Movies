package com.szn.core.network.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Avatar(
    val gravatar: Gravatar,
    val tmdb: Tmdb
): Parcelable

@Parcelize
data class Gravatar(
    val hash: String
): Parcelable

@Parcelize
data class Tmdb(
    val avatar_path: String?
): Parcelable