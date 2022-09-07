package com.szn.movies.domain.model

data class Playlist(val title: String,
                    val movies: List<Video>,
                    var page: Int = 1,
                    var what: String = ""
)