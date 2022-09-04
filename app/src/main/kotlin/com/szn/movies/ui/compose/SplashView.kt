package com.szn.movies.ui.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.szn.movies.ui.navigation.NavRoutes
import com.szn.movies.viewmodel.MoviesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashView(navController: NavHostController, moviesViewModel: MoviesViewModel = hiltViewModel()) {

    CoroutineScope(Dispatchers.Main).launch {
        delay(4000)
        navController.navigate(NavRoutes.Home.route)
    }

    Text(text = "Splash", color = Color.White)
}


