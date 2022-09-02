package com.szn.movies.ui.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.szn.movies.ui.theme.AppTheme

@Composable
fun HomeView(navController: NavHostController) {
    Greeting(name = "Toto")
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello Putain d' $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Greeting("Android")
    }
}