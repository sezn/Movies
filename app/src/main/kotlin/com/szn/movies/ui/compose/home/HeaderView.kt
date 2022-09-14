package com.szn.movies.ui.compose.home

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.szn.movies.domain.model.Playlist
import com.szn.movies.domain.model.fakeMovie
import com.szn.movies.ui.theme.AppTheme
import com.szn.movies.viewmodel.MoviesViewModel

@Composable
fun HeaderView(playlist: Playlist, navController: NavHostController,
                 moviesViewModel: MoviesViewModel = hiltViewModel()
) {
    Log.w("HeaderView", "${playlist.title} ${playlist.movies.size}")

    val videos = moviesViewModel.trendingMovies.collectAsLazyPagingItems()

    Column {

        LazyRow(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            itemsIndexed(videos) { index, item ->
                item?.let {
                    HeaderVideoCard(movie = it) {
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeaderViewPreview(){
    val list = mutableListOf(fakeMovie, fakeMovie, fakeMovie, fakeMovie)
    val playlist = Playlist("Preview", list)
    AppTheme {
        HeaderView(playlist = playlist, navController = rememberNavController())
    }
}

private fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}