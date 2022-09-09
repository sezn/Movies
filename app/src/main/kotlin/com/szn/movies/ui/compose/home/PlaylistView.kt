package com.szn.movies.ui.compose.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.szn.core.network.model.Movie
import com.szn.movies.domain.model.Playlist
import com.szn.movies.domain.model.fakeMovie
import com.szn.movies.ui.compose.common.VideoCard
import com.szn.movies.ui.theme.AppTheme
import com.szn.movies.viewmodel.MoviesViewModel

@Composable
fun PlaylistView(playlist: Playlist, navController: NavHostController,
                 moviesViewModel: MoviesViewModel = hiltViewModel()) {
    Log.w("PlaylistView", "${playlist.title} ${playlist.movies.size}")

//    val videos = moviesViewModel.ratedMovies.collectAsLazyPagingItems()
    val videos = moviesViewModel.popularsPagedFlow.collectAsLazyPagingItems()
    Column {
        Text(text = playlist.title,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            itemsIndexed(videos) { index, item ->
                item?.let {
                    VideoCard(movie = it) {
                        Log.w("PlaylistView", "click on ${it.title} ${it.id}")
                        navController.currentBackStackEntry!!.savedStateHandle.apply {
                            set("id", it.id)
                            set("movie", it)
                        }
                        navController.navigate("movie/{${it.id}}"){
                            launchSingleTop = true
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlaylistView(title: String, items: LazyPagingItems<Movie>, navController: NavHostController) {

    Column {
        Text(text = title,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            itemsIndexed(items) { index, item ->
                item?.let {
                    VideoCard(movie = it) {
                        Log.w("PlaylistView", "click on ${it.title} ${it.id}")
                        navController.currentBackStackEntry!!.savedStateHandle.apply {
                            set("id", it.id)
                            set("movie", it)
                        }
                        navController.navigate("movie/{${it.id}}"){
                            launchSingleTop = true
                        }
                    }
                }
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PlaylistViewPreview(){
    val list = mutableListOf(fakeMovie, fakeMovie, fakeMovie, fakeMovie)
    val playlist = Playlist("Preview", list)
    AppTheme {
        PlaylistView(playlist = playlist, navController = rememberNavController())
    }
}