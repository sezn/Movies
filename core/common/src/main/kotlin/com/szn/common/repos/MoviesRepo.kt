package com.szn.common.repos

import com.szn.common.MoviesRepository
import com.szn.common.mappers.VideoMapper
import com.szn.common.model.Playlist
import com.szn.common.model.Video
import com.szn.common.model.asModel
import com.szn.database.AppDatabase
import com.szn.datastore.DataStoreManager
import com.szn.network.API
import com.szn.network.model.MEDIA_TYPE
import com.szn.network.model.TIME_TYPE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepo @Inject constructor(private val api: API,
                                      private val database: AppDatabase,
                                      private val datastore: DataStoreManager
): MoviesRepository {


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
        val resp = api.getMovies("sort_by=vote_average.asc")
        val videos = mutableListOf<Video>()
        resp.results.map {
            val video = VideoMapper().map(it)
            videos.add(video)
        }
        emit(videos)
    }

    suspend fun getUpComings(): Flow<List<Video>> = flow{
        val resp = api.getUpcomingMovies()
        val videos = mutableListOf<Video>()
        resp.results.map {
            val video = VideoMapper().map(it)
            videos.add(video)
        }
        emit(videos)
    }

    suspend fun getMovie(id: Int): Video {
        return api.getMovie(id).asModel()
    }

/*

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
    }.flow*/
}