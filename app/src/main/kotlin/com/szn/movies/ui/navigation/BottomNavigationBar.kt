package com.szn.movies.ui.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems(LocalContext.current).BarItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                unselectedContentColor = MaterialTheme.colors.onBackground,
                selectedContentColor = MaterialTheme.colors.onBackground,
                modifier =  Modifier.background(MaterialTheme.colors.background),
                onClick = {
                    if(currentRoute.equals(navItem.route)){
                        // Reselect, let recompose
                        navController.popBackStack()
                    }

                    navController.navigate(navItem.route) {
                       popUpTo(navController.graph.findStartDestination().id) {
                           saveState = true
                       }
                        // Avoid multiple copies of the same destination when reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
 
                icon = {
                    Icon(imageVector = navItem.image, 
                           contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }

}

@Composable
fun recomp(context: Context) {
    currentComposer.composition.recompose()

    LaunchedEffect(key1 = "toto"){
        Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
    }
}
