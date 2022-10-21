package com.szn.core.mappers

import com.szn.core.network.model.Movie
import com.szn.common.model.Video

class VideoMapper {

    fun map (movie: Movie): Video {
        return Video(movie.id,
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

