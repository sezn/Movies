package com.szn.movies.ui.compose

import android.util.Log
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
import com.szn.movies.R
import com.szn.movies.ui.navigation.NavRoutes
import com.szn.movies.ui.theme.AppTheme

@Composable
fun CrossApp() {
    val TAG = "CrossApp"
    val navController = rememberNavController()
    var (canPop, setCanPop) = remember { mutableStateOf(false) }

    navController.addOnDestinationChangedListener { controller, _, _ ->
        setCanPop(controller.previousBackStackEntry != null)
    }
    var title = mutableStateOf(stringResource(id = R.string.app_name))
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
            HomeView(navController)
        }

    }

}

