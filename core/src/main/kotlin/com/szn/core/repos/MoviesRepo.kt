package com.szn.core.repos

import com.szn.core.mappers.VideoMapper
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
            val video = VideoMapper().map(it)
            videos.add(video)
        }
        return playlist
    }

    override suspend fun getMovies(what: String, page: Int): Playlist {
        val resp = api.getMovies(null)
        val videos = mutableListOf<Video>()
        val playlist = Playlist("Trendings", videos)
        resp.results.map {
            val video = VideoMapper().map(it)
            videos.add(video)
        }
        return playlist
    }

    override suspend fun getPopulars(): Flow<List<Video>> = flow {
        val resp = api.getMovies(null)
        val videos = mutableListOf<Video>()
        resp.results.map {
            val video = VideoMapper().map(it)
            videos.add(video)
        }
        emit(videos)
    }

    suspend fun getMostRated(): Flow<List<Video>> = flow{
        val resp = api.getMovies("sort_by=vote_average.desc")
        val videos = mutableListOf<Video>()
        resp.results.map {
            val video = VideoMapper().map(it)
            videos.add(video)
        }
        emit(videos)
    }
}