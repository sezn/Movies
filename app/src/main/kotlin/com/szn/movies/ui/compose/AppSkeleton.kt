package com.szn.movies.ui.compose

import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.szn.core.network.utils.Constants
import com.szn.movie.auth.ui.LoginScreen
import com.szn.movies.R
import com.szn.movies.domain.model.Video
import com.szn.movies.ui.compose.detail.VideoView
import com.szn.movies.ui.compose.home.HomeView
import com.szn.movies.ui.navigation.NavRoutes
import com.szn.movies.ui.theme.AppTheme

@Composable
fun AppSkeleton() {
    val TAG = "CrossApp"
    val navController = rememberNavController()
    var (canPop, setCanPop) = remember { mutableStateOf(false) }
    var title = mutableStateOf(stringResource(id = R.string.app_name))
    val showTopBar = remember { mutableStateOf(false) }
    navController.addOnDestinationChangedListener { controller, destination, _ ->
        setCanPop(controller.previousBackStackEntry != null && destination.route != NavRoutes.Home.route)

        if(destination.route != NavRoutes.Splash.route)
            showTopBar.value = true
    }
    Log.w(TAG, "compose ${navController.currentDestination?.route} pop $canPop")

    AppTheme {
        Scaffold(
            modifier = Modifier.testTag(Constants.APP),
            topBar = {
                if(showTopBar.value)
                    TopBar(navController, canPop, title)
                     },
            contentColor = MaterialTheme.colors.onBackground,
            content = {
                NavigationHost(navController = navController)
            }
        )
    }
}

@Composable
fun NavigationHost(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route
    ) {

        composable(NavRoutes.Splash.route) {
            SplashView(navController)
        }

        composable(NavRoutes.Home.route) {
            HomeView(navController)
        }

        composable(NavRoutes.Movie.route) { backStackEntry ->
            val movie = navController.previousBackStackEntry?.savedStateHandle?.get<Video>("movie")

            if (movie != null) {
                VideoView(movie)
            } else Log.e("App", "MEF, Video null!!")
        }


        composable(NavRoutes.Login.route) {
            LoginScreen(navController)
        }
    }
}