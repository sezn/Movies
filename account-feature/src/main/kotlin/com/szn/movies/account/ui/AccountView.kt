package com.szn.movies.account.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.szn.core.R
import com.szn.movie.auth.viewmodel.UserViewModel

@Composable
fun AccountView() {

    val userViewModel: UserViewModel = hiltViewModel()
    Image(painter = painterResource(id = R.drawable.background),
        contentDescription = "BG",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize(),
        colorFilter = ColorFilter.tint(
            Color(0x1A040722),
            BlendMode.Saturation
        )
    )

}