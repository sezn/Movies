package com.szn.movies.ui.compose

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.szn.movies.domain.model.Playlist
import com.szn.movies.ui.theme.AppTheme
import com.szn.movies.viewmodel.MoviesViewModel

@Composable
fun HomeView(navController: NavHostController, moviesViewModel: MoviesViewModel = hiltViewModel()) {

    val TAG = "HomeView"
    val playlists = mutableListOf<Playlist>()

    val lazyVideos = moviesViewModel.trendingMovies.collectAsLazyPagingItems()
    Log.w(TAG, "have ${lazyVideos.itemCount}")
    LazyRow {
        items(lazyVideos){ movie ->
            VideoCard(movie = movie!!, onClick = {})
        }
    }
    LazyColumn(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 12.dp)) {
        itemsIndexed(playlists){ index, playlist ->
            PlaylistView(playlist = playlist, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    AppTheme {
        HomeView(rememberNavController())
    }
}