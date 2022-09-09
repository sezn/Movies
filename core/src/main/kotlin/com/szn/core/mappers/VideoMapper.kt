package com.szn.core.mappers

import com.szn.core.network.model.user.Movie
import com.szn.movies.domain.model.Video

class VideoMapper {

    fun map (movie: Movie): Video {
        return Video(movie.id,
            movie.title,
            movie.overview,
            movie.poster_path,
            movie.backdrop_path,
            movie.release_date)
    }
}

