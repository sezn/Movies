package com.szn.common.model

data class Playlist(val title: String,
                    var movies: List<Video>,
                    var page: Int = 1,
                    var what: String = ""
)