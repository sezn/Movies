package com.szn.movies.ui.compose.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.szn.movies.R
import com.szn.movies.ui.compose.common.PlaylistsView
import com.szn.movies.viewmodel.MoviesViewModel
import com.szn.network.State
import kotlinx.coroutines.launch

@Composable
fun HomeView(navController: NavHostController, viewModel: MoviesViewModel = hiltViewModel()) {
    val TAG = "HomeView"
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()
    Log.w(TAG, "compose $state")
    val homePlaylists = viewModel.homePlaylists

    LaunchedEffect(key1 = "Home", block = {
        scope.launch {
            if(homePlaylists[1].movies.isEmpty())
                viewModel.getHome()
        }
    })


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp, 0.dp, 8.dp, 0.dp),
        color = MaterialTheme.colors.background
    ) {

        state.let { state ->
            Log.w(TAG, "State changed to $state ")
            when(state) {

                State.START, State.LOADING -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(color = colorResource(id = R.color.red))
                    }
                }
                is State.FAILURE -> {
                    val message = state.message
                    LaunchedEffect(key1 = message) {
                        Toast.makeText(navController.context, message, Toast.LENGTH_SHORT).show()
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                scope.launch {
                                    Log.e(TAG, "onClick after fail..")
//                                    viewModel.getMovies()
                                }
                            },
                    ) {
                        Text(text = message, color = colorResource(id = R.color.red),
                            fontWeight = FontWeight.Bold, fontSize = 28.sp,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                        )
                        CircularProgressIndicator(color = colorResource(id = R.color.red))
                    }
                }
                State.SUCCESS -> {
                    Log.e(TAG, "State SUCCESS!!! ")
                    PlaylistsView(navController, homePlaylists)
                    }

                }
            }
        }
}