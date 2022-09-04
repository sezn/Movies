package com.szn.movies.ui.compose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.szn.core.network.utils.Constants
import com.szn.movies.ui.navigation.NavRoutes
import com.szn.movies.ui.theme.AppTheme

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppSkeleton() {
    val TAG = "CrossApp"
    val navController = rememberAnimatedNavController()
    var (canPop, setCanPop) = remember { mutableStateOf(false) }

    navController.addOnDestinationChangedListener { controller, _, _ ->
        setCanPop(controller.previousBackStackEntry != null)
    }
    Log.w(TAG, "compose ${navController.currentDestination?.route}")

    AppTheme {
        Scaffold(
            modifier = Modifier.testTag(Constants.APP),
            content = {
                NavigationHost(navController = navController)
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationHost(navController: NavHostController){

    AnimatedNavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route
    ) {

        composable(NavRoutes.Splash.route,
            enterTransition = {
//                when (initialState.destination.route) {
                    return@composable slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
//                }
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            ) {
            SplashView(navController)
        }

        composable(NavRoutes.Home.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            }
            ) {
            HomeView(navController)
        }

    }

}

