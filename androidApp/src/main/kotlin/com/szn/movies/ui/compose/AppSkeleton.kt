package com.szn.movies.ui.compose

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.szn.movie.auth.ui.LoginScreen
import com.szn.movies.R
import com.szn.movies.account.ui.AccountView
import com.szn.common.model.Video
import com.szn.movies.ui.compose.components.TopBar
import com.szn.movies.ui.compose.detail.VideoView
import com.szn.movies.ui.compose.home.HomeView
import com.szn.movies.ui.navigation.BottomNavigationBar
import com.szn.movies.ui.navigation.NavRoutes
import com.szn.movies.ui.theme.AppTheme

@Composable
fun AppSkeleton() {
    val TAG = "CrossApp"
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    var (canPop, setCanPop) = remember { mutableStateOf(false) }
    val mainTitle = stringResource(id = R.string.app_name)
    var title = remember { mutableStateOf(mainTitle) }
    val showTopBar = remember { mutableStateOf(false) }
    val showBottomBar = remember { mutableStateOf(false) }
    val showLogout = remember { mutableStateOf(false) }

    val homeScreens = mutableListOf(NavRoutes.Home.route, NavRoutes.Account.route)//, NavRoutes.Account.route)
    val screensWithoutTop = mutableListOf(NavRoutes.Splash.route, NavRoutes.Login.route)//, NavRoutes.Account.route)
    val screensWithoutBottom = mutableListOf(NavRoutes.Splash.route, NavRoutes.Login.route)

    navController.addOnDestinationChangedListener { controller, destination, _ ->
        setCanPop(controller.previousBackStackEntry != null && destination.route !in homeScreens)
        showTopBar.value = destination.route !in screensWithoutTop
        showLogout.value = showTopBar.value
        showBottomBar.value = destination.route !in screensWithoutBottom
    }
    Log.w(TAG, "compose ${navController.currentDestination?.route} pop $canPop")

    AppTheme {
        Scaffold(
            modifier = Modifier.testTag("MoviesApp"),
            topBar = {
                if(showTopBar.value)
                    TopBar(navController, scope, canPop, showLogout.value, title)
                     },
            contentColor = MaterialTheme.colors.onBackground,
            content = { padding ->
                NavigationHost(navController = navController, modifier = Modifier.padding(padding))
            },
            bottomBar = {
                if(showBottomBar.value)
                    BottomNavigationBar(navController = navController)
            }
        )
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier){

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route,
        modifier = modifier
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

        composable(NavRoutes.Account.route) {
            AccountView()
        }
    }
}
