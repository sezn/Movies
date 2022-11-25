package com.szn.movies.ui.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import com.szn.common.R

class NavBarItems(context: Context) {
    val BarItems = listOf(
        BarItem(
            title = context.getString(com.szn.movies.R.string.app_name),
            image = Icons.Filled.Home,
            route = NavRoutes.Home.route
        ),
        /*BarItem(
            title = context.getString(R.string.search),
            image = Icons.Filled.Search,
            route = NavRoutes.Search.route
        ),*/
        BarItem(
            title = context.getString(R.string.my_account),
            image = Icons.Filled.PlayArrow,
            route = NavRoutes.Account.route
        )
    )
}