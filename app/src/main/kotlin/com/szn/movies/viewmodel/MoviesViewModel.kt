package com.szn.movies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.szn.core.datasource.MoviesMediator
import com.szn.core.datastore.DataStoreManager
import com.szn.core.db.AppDatabase
import com.szn.core.network.API
import com.szn.core.repos.MoviesRepo
import com.szn.movies.datasource.MoviesDataSource
import com.szn.movies.datasource.TrendingsDataSource
import com.szn.movies.domain.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesApi: API,
                                          private val moviesRepo: MoviesRepo,
                                          private val database: AppDatabase,
                                          private val datastore: DataStoreManager
): ViewModel() {

    val state = moviesRepo.state

    init {
        viewModelScope.launch {
            getMovies()
        }

    }

    private suspend fun getMovies() {
        val movies = moviesApi.getMovies(null)
        Log.w(TAG, "getMovies ${movies.results.size}")
    }

    val trendingMovies: Flow<PagingData<Video>> = Pager(PagingConfig(pageSize = 20)) {
        TrendingsDataSource(moviesRepo)
    }.flow

    val ratedMovies: Flow<PagingData<Video>> = Pager(PagingConfig(pageSize = 20)) {
        MoviesDataSource(moviesRepo, "sort_by=vote_average.desc")
    }.flow

    val recentMovies: Flow<PagingData<Video>> = Pager(PagingConfig(pageSize = 20)) {
        MoviesDataSource(moviesRepo, "primary_release_year=2022&sort_by=vote_average.desc")
    }.flow

    @OptIn(ExperimentalPagingApi::class)
    val pagedFlow = Pager(
        PagingConfig(pageSize = 20),
        remoteMediator = MoviesMediator(moviesApi, database, datastore)
    ) {
        database.movieDao().pagingSource()
    }.flow

    suspend fun getPopulars() = moviesRepo.getPopulars()
    suspend fun getMostRated() = moviesRepo.getMostRated()

    companion object {
        val TAG = MoviesViewModel::class.java.simpleName
    }

}

