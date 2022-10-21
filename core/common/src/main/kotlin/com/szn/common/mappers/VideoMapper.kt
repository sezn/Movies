package com.szn.common.mappers

import com.szn.common.model.Video
import com.szn.network.model.Movie

class VideoMapper {

    fun map (movie: Movie): Video {
        return Video(
            movie.id,
            movie.title,
            movie.getGenders(),
            movie.overview,
            movie.poster_path,
            movie.backdrop_path,
            movie.release_date,
            movie.runtime
        )
    }
}