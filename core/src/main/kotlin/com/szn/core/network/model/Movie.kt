package com.szn.core.network.model

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
    val vote_count: Int
) {

    constructor(id: Int, title: String, poster_path: String): this(id, title, false,null,null, null, null, null, null, poster_path, null, null, null, 0)

    fun getImage(): String {
        return poster_path.toString()
    }
}

val fakeMovie = Movie(0, "Toto", "https://image.tmdb.org/t/p/w500/qsGrZgwOs8B6Jqen0ECBJ6UgfEG.jpg")

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