package com.szn.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.szn.common.Constants
import com.szn.common.model.Playlist
import com.szn.common.model.Video
import com.szn.common.model.fakeMovie
import com.szn.common.repos.MoviesRepo
import com.szn.common.extensions.flattenToList
import com.szn.movies.datasource.TrendingsDataSource
import com.szn.network.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepo: MoviesRepo): ViewModel() {

    val state by lazy { MutableStateFlow<State>(State.LOADING) }
    val movieState by lazy { MutableStateFlow<State>(State.START) }

    val trendingMovies: Flow<PagingData<Video>> = Pager(PagingConfig(pageSize = 20)) {
        TrendingsDataSource(moviesRepo)
    }.flow

    private val popsPlaylist = Playlist("Les plus populaires", mutableListOf(), what = Constants.POPULARS)
    private val comingPlaylist = Playlist("Les films à venir", mutableListOf(), what = Constants.UPCOMMINGS)
    private val ratedPlaylist = Playlist("Les mieux notés", mutableListOf(), what = "sort_by=vote_average.desc")

    val homePlaylists = mutableListOf(
        Playlist("Header", mutableListOf()),
        popsPlaylist, comingPlaylist, ratedPlaylist
    )

    suspend fun getHome() {
        popsPlaylist.movies = moviesRepo.getPopulars().flattenToList()
        comingPlaylist.movies = moviesRepo.getUpComings().flattenToList()
        ratedPlaylist.movies = moviesRepo.getMostRated().flattenToList()

        state.emit(State.SUCCESS)
    }

    suspend fun getMovie(id: Int): Video {
        movieState.emit(State.LOADING)
        val mv = moviesRepo.getMovie(id)
        val video = fakeMovie
//        val video = VideoMapper().map(mv)
//        movieState.emit(State.SUCCESS)
        movieState.value = State.SUCCESS
        return video
    }

    companion object {
        val TAG = MoviesViewModel::class.java.simpleName
    }

}

