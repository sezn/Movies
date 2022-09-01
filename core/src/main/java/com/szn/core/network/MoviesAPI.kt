package com.szn.core.network

import com.szn.core.network.model.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API{

    @GET("4/discover/movie")
    suspend fun getMovies(@Query("sort_by") sort: String?,
                          @Query("language") lang: String? = "fr",
                          @Query("page") page: Int? = 1): Movies

    /**
     * Note: not working on 4..
     * @param media_type: all, movie, tv, person
     * @param time_window: day, week
     */
    @GET("3/trending/{media_type}/{time_window}")
    suspend fun getTrendings(@Path("media_type") type: String,
                             @Path("time_window") time: String,
                             @Query("page") page: Int? = 1): Movies
}