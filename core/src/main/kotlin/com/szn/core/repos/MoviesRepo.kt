package com.szn.core.repos

import com.szn.core.network.API
import com.szn.core.network.model.MEDIA_TYPE
import com.szn.core.network.model.TIME_TYPE
import com.szn.movies.domain.MoviesRepository
import com.szn.movies.domain.model.Playlist
import com.szn.movies.domain.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepo @Inject constructor(private val api: API): MoviesRepository {

    override suspend fun getTrendings(page: Int): Playlist {
        val resp = api.getTrendings(MEDIA_TYPE.movie.name, TIME_TYPE.day.name, page)
        val videos = mutableListOf<Video>()
        val playlist = Playlist("Trendings", videos)

        resp.results.map {
            val video = Video(it.id, it.title, it.poster_path!!)
            videos.add(video)
        }

        return playlist
    }

    override suspend fun getPopulars(): Flow<List<Video>> = flow {
//        TODO: Convert from Movie to Video
        val movies = api.getMovies(null)
        val videos = mutableListOf<Video>()

        emit(videos)
    }
}