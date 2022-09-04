package com.szn.movies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.szn.core.network.API
import com.szn.core.repos.MoviesRepo
import com.szn.movies.datasource.MoviesDataSource
import com.szn.movies.domain.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesApi: API,
                        private val moviesRepo: MoviesRepo): ViewModel() {

    init {
        viewModelScope.launch {
            getMovies()
        }

    }

    private suspend fun getMovies() {
        val movies = moviesApi.getMovies(null)
        Log.w(TAG, "getMovies ${movies.results.size}")
//        val movie = movies.results[0].poster_path

    }

    val trendingMovies: Flow<PagingData<Video>> = Pager(PagingConfig(pageSize = 20)) {
        MoviesDataSource(moviesRepo)
    }.flow

    companion object {
        val TAG = MoviesViewModel::class.java.simpleName
    }

}