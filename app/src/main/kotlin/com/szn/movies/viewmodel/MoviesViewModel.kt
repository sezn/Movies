package com.szn.movies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.szn.core.network.API
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesApi: API): ViewModel() {

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

    companion object {
        val TAG = MoviesViewModel::class.java.simpleName
    }

}