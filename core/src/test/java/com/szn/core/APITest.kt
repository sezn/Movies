package com.szn.core

import com.szn.core.network.API
import com.szn.core.network.model.MEDIA_TYPE
import com.szn.core.network.model.TIME_TYPE
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class APITest {

    private lateinit var service: API

    @Before
    fun setUp() {
        service = Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIES_BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor{chain ->
                    val original = chain.request()
                    val url = original.url.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
                        .build()
                    // Request customization: add request headers
                    val requestBuilder = original.newBuilder()
                        .url(url)
                    chain.proceed(requestBuilder.build())
                }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)
    }

    @Test
    fun testGetMovies(){
        runBlocking {
            val movies = service.getMovies(null)
            assert(movies.results.isNotEmpty())
        }
    }

    @Test
    fun testGetTrendings(){
        runBlocking {
            val movies = service.getTrendings(MEDIA_TYPE.movie.name, TIME_TYPE.day.name)
            assert(movies.results.isNotEmpty())
        }
    }


}