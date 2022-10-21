package com.szn.core.network

import com.szn.core.network.model.session.AuthResult
import com.szn.core.network.model.user.Account
import com.szn.network.model.Movie
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface API {

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
    suspend fun login(@Body params: RequestBody): Response<AuthResult>

    @GET("3/account")
    suspend fun getAccount(@Query("session_id") sessId: String): Account

    @DELETE("3/authentication/session")
    suspend fun logout(@Query("session_id") sessId: String): Response<AuthResult>
/*
    @GET("3/account/{account_id}/favorite/movies")
    suspend fun getFavorites(@Path("account_id") accountId: String,
                             @Query("session_id") sessId: String,
                             @Query("language") lang: String? = "fr",
                             @Query("page") page: Int? = 1): Movies*/

    @POST("3/account/{account_id}/favorite")
    suspend fun favorite(
        @Path("account_id") accountId: String,
        @Query("session_id") sessId: String,
        @Body params: RequestBody
    ): Response<List<Movie>>
}