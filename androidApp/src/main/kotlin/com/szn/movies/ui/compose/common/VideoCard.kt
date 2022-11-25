package com.szn.movies.ui.compose.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.material.Card
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

@Composable
fun VideoCard(movie: Video, onClick: (Video) -> Unit){
    val TAG = "VideoCard"

    Card(
        modifier = Modifier.clickable { onClick.invoke(movie) },
        elevation = 10.dp
    ) {

        AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(movie.getImage()).build(),
            contentDescription = movie.title,
            contentScale = ContentScale.FillBounds)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideoCardPreview(){
    VideoCard(movie = fakeMovie, onClick = {})
}