package com.szn.movies.domain

import com.szn.movies.domain.model.Playlist
import kotlinx.coroutines.flow.Flow
import com.szn.movies.domain.model.Video

interface MoviesRepository {

//    Trendings
    suspend fun getTrendings(page: Int): Playlist
//    Populars
    suspend fun getPopulars(): Flow<List<Video>>
//    soon
//    mostRated


}