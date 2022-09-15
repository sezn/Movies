package com.szn.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.szn.core.Constants
import com.szn.core.datastore.DataStoreManager
import com.szn.core.extensions.flattenToList
import com.szn.core.mappers.VideoMapper
import com.szn.core.network.State
import com.szn.core.repos.MoviesRepo
import com.szn.movies.datasource.TrendingsDataSource
import com.szn.movies.domain.model.Playlist
import com.szn.movies.domain.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepo: MoviesRepo,
                                          private val datastore: DataStoreManager
): ViewModel() {

    val state = moviesRepo.state
    val movieState by lazy { MutableStateFlow<State>(State.START) }

    init {
        viewModelScope.launch {
            delay(2000) // Test TODO: remove
            state.emit(State.SUCCESS)
        }

    }
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
    }

    suspend fun getMovie(id: Int): Video {
        movieState.emit(State.LOADING)
        val mv = moviesRepo.getMovie(id)
        val video = VideoMapper().map(mv)
        movieState.emit(State.SUCCESS)
        return video
    }

    companion object {
        val TAG = MoviesViewModel::class.java.simpleName
    }

}

