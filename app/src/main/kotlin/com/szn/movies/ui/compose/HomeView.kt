package com.szn.movies.ui.compose

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.szn.movies.domain.model.Playlist
import com.szn.movies.domain.model.fakeMovie
import com.szn.movies.ui.theme.AppTheme
import com.szn.movies.viewmodel.MoviesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun HomeView(navController: NavHostController, moviesViewModel: MoviesViewModel = hiltViewModel()) {

    val TAG = "HomeView"
    val playlists = mutableListOf<Playlist>()

    val lazyVideos = moviesViewModel.trendingMovies.collectAsLazyPagingItems()
    Log.w(TAG, "have ${lazyVideos.itemCount}")

    playlists.add(Playlist("Top", mutableListOf(), what = "sort_by=vote_average.desc"))
    playlists.add(Playlist("Les plus populaires", mutableListOf(), what = "sort_by=vote_average.desc"))
    playlists.add(Playlist("Les films à venir", mutableListOf(), what = "primary_release_year=2022&sort_by=vote_average.desc"))

    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        itemsIndexed(playlists) { index, playlist ->
            if (index == 0)
                HeaderView(playlist = playlist, navController)
            else
                PlaylistView(playlist = playlist, navController)
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    AppTheme {
        HomeView(rememberNavController())
    }
}