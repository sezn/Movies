package com.szn.core.repos

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.szn.core.Constants
import com.szn.core.datasource.MoviesMediator
import com.szn.core.datastore.DataStoreManager
import com.szn.core.db.AppDatabase
import com.szn.core.mappers.VideoMapper
import com.szn.core.network.API
import com.szn.core.network.State
import com.szn.core.network.model.MEDIA_TYPE
import com.szn.core.network.model.TIME_TYPE
import com.szn.movies.domain.MoviesRepository
import com.szn.movies.domain.model.Playlist
import com.szn.movies.domain.model.Video
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepo @Inject constructor(private val api: API,
                                     private val database: AppDatabase,
                                    private val datastore: DataStoreManager): MoviesRepository {

    val TAG = "MoviesRepo"
    val state by lazy { MutableStateFlow<State>(State.LOADING) }

    init {
        Log.w(TAG, "init")
        CoroutineScope(Dispatchers.IO).launch {
            val movies = database.movieDao().getAll()
            Log.w(TAG, "init ${movies.size}")
        }
    }

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

    @OptIn(ExperimentalPagingApi::class)
    val pagedFlow = Pager(
        PagingConfig(pageSize = 20),
        remoteMediator = MoviesMediator(api, database, datastore, null)
    ) {
        database.movieDao().pagingSource()
    }.flow

    @OptIn(ExperimentalPagingApi::class)
    val popularPagedFlow = Pager(
        PagingConfig(pageSize = 20),
        remoteMediator = MoviesMediator(api, database, datastore, Constants.POPULARS)
    ) {
        database.movieDao().pagingPopularSource()
    }.flow

    @OptIn(ExperimentalPagingApi::class)
    val upcomingsPagedFlow = Pager(
        PagingConfig(pageSize = 20),
        remoteMediator = MoviesMediator(api, database, datastore, Constants.UPCOMMINGS)
    ) {
        database.movieDao().pagingTopRatedSource()
    }.flow
}