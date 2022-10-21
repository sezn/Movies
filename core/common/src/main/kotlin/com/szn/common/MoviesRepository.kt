package com.szn.common

import com.szn.common.model.Playlist
import com.szn.common.model.Video
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

//    Trendings
    suspend fun getTrendings(page: Int): Playlist

    suspend fun getMovies(what: String, page: Int): Playlist
//    Populars
    suspend fun getPopulars(): Flow<List<Video>>
//    soon
//    mostRated


}