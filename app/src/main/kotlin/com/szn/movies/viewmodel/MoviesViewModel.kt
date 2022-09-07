package com.szn.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.szn.core.datastore.DataStoreManager
import com.szn.core.network.State
import com.szn.core.repos.MoviesRepo
import com.szn.movies.datasource.MoviesDataSource
import com.szn.movies.datasource.TrendingsDataSource
import com.szn.movies.domain.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepo: MoviesRepo,
                                          private val datastore: DataStoreManager
): ViewModel() {

    val state = moviesRepo.state

    init {
        viewModelScope.launch {
            delay(2000) // Test TODO: remove
            state.emit(State.SUCCESS)
        }

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


    val pagedFlow = moviesRepo.pagedFlow
    val upcomingsFlow = moviesRepo.upcomingsPagedFlow
    val popularsPagedFlow = moviesRepo.popularPagedFlow

    suspend fun getPopulars() = moviesRepo.getPopulars()
    suspend fun getMostRated() = moviesRepo.getMostRated()

    companion object {
        val TAG = MoviesViewModel::class.java.simpleName
    }

}

