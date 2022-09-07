package com.szn.movies.ui.compose.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.szn.movies.domain.model.Playlist
import com.szn.movies.ui.compose.home.HeaderView
import com.szn.movies.ui.compose.home.PlaylistView
import com.szn.movies.ui.compose.home.homePlaylists
import com.szn.movies.ui.theme.AppTheme
import com.szn.movies.viewmodel.MoviesViewModel

@Composable
fun PlaylistsView(navController: NavHostController, moviesViewModel: MoviesViewModel = hiltViewModel()) {

    val pops = moviesViewModel.popularsPagedFlow.collectAsLazyPagingItems()
    val upcomings = moviesViewModel.upcomingsFlow.collectAsLazyPagingItems()
    val rated = moviesViewModel.pagedFlow.collectAsLazyPagingItems()

    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        itemsIndexed(homePlaylists) { index, playlist ->
            if (index == 0)
                HeaderView(playlist = playlist, navController)
            else if (index == 1)
                PlaylistView(playlist.title, pops, navController)
            else if (index == 2)
                PlaylistView(playlist.title, upcomings, navController)
            else
                PlaylistView(playlist.title, rated, navController)
        }
    }

}

@Preview
@Composable
fun PlaylistsViewPreview() {
    AppTheme {
        PlaylistsView(rememberNavController())
    }
}