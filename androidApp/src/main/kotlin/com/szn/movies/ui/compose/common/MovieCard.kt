package com.szn.movies.ui.compose.common

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.szn.common.mappers.VideoMapper
import com.szn.common.model.Video
import com.szn.common.model.fakeMovie
import com.szn.network.model.Movie

@Composable
fun VideoCard(movie: Movie, onClick: (Video) -> Unit){
    val TAG = "VideoCard"

    val img = movie.poster_path
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .clickable {
                onClick.invoke(VideoMapper().map(movie))
            },
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {

            AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(img).build(),
                contentDescription = movie.title,
//                previewPlaceholder = R.drawable.minions,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .size(135.dp))


        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieCardPreview(){
    VideoCard(movie = fakeMovie, onClick = {})
}
