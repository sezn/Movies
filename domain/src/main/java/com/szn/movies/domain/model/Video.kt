package com.szn.movies.domain.model

import android.os.Parcelable
import com.szn.movies.domain.BuildConfig
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val id: Int,
    val title: String,
    val adult: Boolean = false,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int
) : Parcelable {
    constructor(id: Int, title: String, poster_path: String, back_path: String): this(id, title, false, back_path,null, null, null, null, null, poster_path, null, null, null, 0)

    fun getImage(): String? {
        return BuildConfig.IMAGE_BASE + poster_path
    }

    fun getBackImage(): String? {
        return BuildConfig.IMAGE_BASE + backdrop_path
    }

}

val fakeMovie = Video(0, "Toto", "https://image.tmdb.org/t/p/w500/qsGrZgwOs8B6Jqen0ECBJ6UgfEG.jpg", "https://image.tmdb.org/t/p/w500/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg")
