package com.szn.movies.ui.compose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.szn.core.network.utils.Constants
import com.szn.movies.domain.model.Video
import com.szn.movies.ui.compose.detail.VideoView
import com.szn.movies.ui.compose.home.HomeView
import com.szn.movies.ui.compose.home.PlaylistsView
import com.szn.movies.ui.navigation.NavRoutes
import com.szn.movies.ui.theme.AppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppSkeleton() {
    val TAG = "CrossApp"
    val navController = rememberNavController()
    var (canPop, setCanPop) = remember { mutableStateOf(false) }

    navController.addOnDestinationChangedListener { controller, _, _ ->
        setCanPop(controller.previousBackStackEntry != null)
    }
    Log.w(TAG, "compose ${navController.currentDestination?.route}")

    AppTheme {
        Scaffold(
            modifier = Modifier.testTag(Constants.APP),
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
        startDestination = NavRoutes.Home.route
    ) {

        composable(NavRoutes.Splash.route) {
            SplashView(navController)
        }

        composable(NavRoutes.Home.route) {
            PlaylistsView(navController)
        }

        composable(NavRoutes.Movie.route) { backStackEntry ->
            val movie = navController.previousBackStackEntry?.savedStateHandle?.get<Video>("movie")

            if (movie != null) {
                VideoView(movie)
            } else Log.e("App", "MEF, Video null!!")
        }



    }

}

