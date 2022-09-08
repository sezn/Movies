package com.szn.core.network

import com.szn.core.network.model.Movies
import com.szn.core.network.model.session.AuthResult
import okhttp3.RequestBody
import retrofit2.http.*

interface API {

    @GET("4/discover/movie")
    suspend fun getMovies(@Query("sort_by") sort: String?,
                          @Query("language") lang: String? = "fr",
                          @Query("page") page: Int? = 1): Movies

    /**
     * This is a release type query that looks for all movies
     * that have a release type of 2 or 3 within the specified date range.
     */
    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(@Query("language") lang: String? = "fr",
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


//    Auth

    @GET("3/authentication/token/new")
    suspend fun authenticate(): AuthResult

    @POST("3/authentication/session/new")
    suspend fun getSessionId(@Body params: RequestBody): AuthResult

    @GET("4/auth/request_token")
    suspend fun requestToken(): AuthResult

    /**
     * This method will let you create a new guest session.
     * Guest sessions are a type of session that will let a user rate movies
     * and TV shows but not require them to have a TMDB user account
     */
    @POST("3/authentication/guest_session/new")
    suspend fun createSession(): AuthResult

    /**
     * This method allows an application to validate a request token by entering a username and password.
     * @param username
     * @param password
     * @param request_token
     */
    @POST("3/authentication/token/validate_with_login")
    suspend fun login(@Body params: RequestBody): AuthResult

    @GET("3/account")
    suspend fun getAccount(@Query("session_id") sessId: String): AuthResult

    @GET("/account/{account_id}/favorite/movies")
    suspend fun getFavorites(@Path("account_id") accountId: String,
                             @Query("session_id") sessId: String,
                             @Query("language") lang: String? = "fr",
                             @Query("page") page: Int? = 1): Movies

    @POST("/account/{account_id}/favorite")
    suspend fun favorite(@Path("account_id") accountId: String,
                         @Query("session_id") sessId: String): Movies
}