package com.szn.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Movie(
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
    val vote_count: Int?,
    val genres: List<Genre>?,
    val runtime: Int?
) {
    fun getGenders(): List<String> {
        val genders = mutableListOf<String>()
        genres?.forEach {
            genders.add(it.name)
        }
        return genders
    }
}

@Parcelize
data class Genre(
    val id: Int,
    val name: String
): Parcelable


enum class TIME_TYPE{
    day,
    week
}
enum class MEDIA_TYPE{
    all,
    movie,
    tv,
    person
}