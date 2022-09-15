package com.szn.movies.ui.compose

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.szn.core.R
import com.szn.movie.auth.viewmodel.UserViewModel
import com.szn.movies.ui.navigation.NavRoutes

@Composable
fun SplashView(navController: NavHostController) {
    val TAG = "SplashView"
    val userViewModel: UserViewModel = hiltViewModel()
    val isLogged = userViewModel.isLogged
    Log.w(TAG, "IsLogged: $isLogged")
    val scale = remember {
        Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(5f).getInterpolation(it)
                })
        )
        navController.navigate(NavRoutes.Login.route)
    }

    // Image
    Image(painter = painterResource(id = R.drawable.background),
        contentDescription = "BG",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.movienight),
            contentDescription = "Logo",
            modifier = Modifier
                .size(320.dp)
                .scale(scale.value))
    }

}


@Preview
@Composable
fun PreviewSplash(){
    SplashView(navController = rememberNavController())
}

