package com.szn.movies.ui.compose.home

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
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
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.szn.movies.domain.model.Playlist
import com.szn.movies.domain.model.fakeMovie
import com.szn.movies.ui.theme.AppTheme
import com.szn.movies.viewmodel.MoviesViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HeaderView(playlist: Playlist, navController: NavHostController,
                 moviesViewModel: MoviesViewModel = hiltViewModel()
) {
    Log.w("PlaylistView", "${playlist.title} ${playlist.movies.size}")

    // We start the pager in the middle of the raw number of pages
    val pageCount = 20
    val startIndex = 0
    val pagerState = rememberPagerState(initialPage = startIndex)

    val videos = moviesViewModel.trendingMovies.collectAsLazyPagingItems()
/*
    HorizontalPager(
        // Set the raw page count to a really large number
        count = Int.MAX_VALUE,
        state = pagerState,
        // Add 32.dp horizontal padding to 'center' the pages
        contentPadding = PaddingValues(horizontal = 32.dp),
        // Add some horizontal spacing between items
        itemSpacing = 4.dp,
        modifier = Modifier
//            .weight(1f)
            .fillMaxWidth()
    ) { index ->
        val page = (index - startIndex).floorMod(pageCount)
        if(videos.itemCount > 0) {
            val video = videos[page]
            video?.let { HeaderVideoCard(movie = it, onClick = {}) }
        }
    }*/

    Column {
        Text(text = playlist.title,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(8.dp))

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