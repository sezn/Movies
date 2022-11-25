package com.szn.movies.ui.compose.home

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.szn.common.model.Video
import com.szn.common.model.fakeMovie
import com.szn.movies.ui.theme.AppTheme

@Composable
fun HeaderVideoCard(movie: Video, onClick: (Video) -> Unit){
    val TAG = "VideoCard"
    Log.e(TAG, "init ${movie.title} ${movie.getImage()}  ${movie.poster_path} ")

    Card(
        modifier = Modifier
//            .height(200.dp)
            .width(400.dp)
            .clickable { onClick.invoke(movie) },
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)
        ) {

            AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(movie.getBackImage()).build(),
                contentDescription = movie.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeaderCardPreview(){
    AppTheme {
        HeaderVideoCard(movie = fakeMovie, onClick = {})
    }
}