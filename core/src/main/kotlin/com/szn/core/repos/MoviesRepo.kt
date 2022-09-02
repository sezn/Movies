package com.szn.core.repos

import com.szn.core.network.API
import com.szn.core.network.model.MEDIA_TYPE
import com.szn.core.network.model.TIME_TYPE
import com.szn.movies.domain.MoviesRepository
import com.szn.movies.domain.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepo(private val api: API): MoviesRepository {

    override suspend fun getTrendings(): Flow<List<Video>> {
//        val vids = api.getTrendings(MEDIA_TYPE.movie.name, TIME_TYPE.day.name)
//        val result: MutableList<Video> = mutableListOf()
//        vids.results.map {
//
//        }
//        result
        TODO("...")
    }

    override suspend fun getPopulars(): Flow<List<Video>> = flow {
//        TODO: Convert from Movie to Video
        val movies = api.getMovies(null)
        val videos = mutableListOf<Video>()

        emit(videos)
    }
}