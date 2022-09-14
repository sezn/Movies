package com.szn.movies.ui.compose.common

import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.skydoves.landscapist.glide.GlideImage
import com.szn.movies.R
import com.szn.movies.domain.model.Video
import com.szn.movies.domain.model.fakeMovie

@Composable
fun VideoCard(movie: Video, onClick: (Video) -> Unit){
    val TAG = "VideoCard"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .clickable { onClick.invoke(movie) },
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
            GlideImage(imageModel = movie.getImage(),
                contentDescription = movie.title,
                previewPlaceholder = R.drawable.minions,
                contentScale = ContentScale.Fit,
                requestListener = object: RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        Log.e(TAG, "onLoadFailed ${e?.localizedMessage}  ${movie.title} ${movie.getImage()}  ${movie.poster_path} ")
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return true
                    }
                } ,
                modifier = Modifier.fillMaxSize()
                    .size(135.dp))


        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideoCardPreview(){
    VideoCard(movie = fakeMovie, onClick = {})
}