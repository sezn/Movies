package com.szn.movies.ui.compose

import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.color.MaterialColors
import com.skydoves.landscapist.glide.GlideImage
import com.szn.core.network.model.Movie
import com.szn.core.network.model.fakeMovie
import com.szn.movies.R

@Composable
fun VideoCard(movie: Movie, onClick: (Movie) -> Unit){
    val TAG = "VideoCard"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke(movie) },
        elevation = 10.dp
    ) {
        Column {
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
                    .background(backgroundColor)
                    .size(135.dp))


        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun VideoCardPreview(){
    VideoCard(movie = fakeMovie, onClick = {})
}