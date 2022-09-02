package com.szn.movies.ui.navigation

sealed class NavRoutes(val route: String) {
    object Splash : NavRoutes(SPLASH)
    object Home : NavRoutes(HOME)
    object Movie : NavRoutes("$VIDEO/{id}")
    object CreateAccount : NavRoutes(CREATE)
    object Login : NavRoutes(LOGIN)
    object Account : NavRoutes("account")
}
const val SPLASH = "splash"
const val HOME = "home"
const val VIDEO = "movie"
const val CREATE = "create"
const val LOGIN = "login"
