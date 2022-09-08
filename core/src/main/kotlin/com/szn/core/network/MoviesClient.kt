package com.szn.core.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.szn.core.BuildConfig
import com.szn.core.network.error.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesClient {

    private val httpInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }

    val httpClient: OkHttpClient by lazy {
        httpInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor{chain ->
                chain.proceed(chain.request().newBuilder()
                    .addHeader("api_key", BuildConfig.API_KEY)
                    .build())
            }
            .addInterceptor{ chain ->
                val original = chain.request()
                val url = original.url.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()

                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .url(url)

                chain.proceed(requestBuilder.build())
            }
            .addInterceptor(httpInterceptor)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIES_BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun moviesService(@ApplicationContext app: Context): API = retrofit.create(API::class.java)
}