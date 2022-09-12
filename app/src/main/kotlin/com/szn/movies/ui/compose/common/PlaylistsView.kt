package com.szn.movies.ui.compose.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.szn.movies.domain.model.Playlist
import com.szn.movies.ui.compose.home.HeaderView
import com.szn.movies.ui.compose.home.PlaylistView

@Composable
fun PlaylistsView(navController: NavHostController, playlists: MutableList<Playlist>) {

    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        itemsIndexed(playlists) { index, playlist ->
            if(index == 0)
                HeaderView(playlist = playlist, navController)
            else
                PlaylistView(playlist, navController)
        }
    }

}

/*
@Preview
@Composable
fun PlaylistsViewPreview() {
    AppTheme {
        PlaylistsView(rememberNavController())
    }
}*/
