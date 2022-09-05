package com.szn.movies.ui.compose.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.szn.movies.domain.model.Video

@Composable
fun VideoView (video: Video){
    Text(text = video.title, color = Color.White)
}