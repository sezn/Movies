package com.szn.movies.domain

import kotlinx.coroutines.flow.Flow
import com.szn.movies.domain.model.Video

interface MoviesRepository {

//    Trendings
    suspend fun getTrendings(): Flow<List<Video>>
//    Populars
    suspend fun getPopulars(): Flow<List<Video>>
//    soon
//    mostRated


}