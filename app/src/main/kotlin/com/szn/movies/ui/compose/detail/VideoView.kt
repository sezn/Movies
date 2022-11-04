package com.szn.movies.ui.compose.detail

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.szn.common.extensions.toYear
import com.szn.movie.auth.ui.dialogs.ErrorDialog
import com.szn.movie.auth.viewmodel.UserViewModel
import com.szn.movies.R
import com.szn.movies.actions.FAV
import com.szn.movies.actions.IconItem
import com.szn.movies.actions.movieActions
import com.szn.common.model.Video
import com.szn.common.model.fakeMovie
import com.szn.movies.ui.theme.AppTheme
import com.szn.movies.viewmodel.MoviesViewModel
import kotlinx.coroutines.launch
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun VideoView (video: Video,
               userViewModel: UserViewModel = hiltViewModel(),
               videoViewModel: MoviesViewModel = hiltViewModel()){

    val TAG = "VideoView"
    val genders = remember { mutableStateOf("") }
    val duration = remember { mutableStateOf("") }
    val openDialog = remember { userViewModel.showError}
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = "Video", block = {
        scope.launch {
            val vid = videoViewModel.getMovie(video.id)
            duration.value = "${vid.duration?.toDuration(DurationUnit.MINUTES)}"
            genders.value = vid.getGenders()
        }
    })

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .testTag(TAG)
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {

        HeaderView(video)

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
        ) {

            Text(
                text = video.title,
                style = MaterialTheme.typography.h2,
            )

            video.release_date?.let {
                Text(
                    text = toYear(it),
                    style = MaterialTheme.typography.h6
                )
            }

            GenderView(genders, duration)

            ActionsView { item, checked ->
                Log.w(TAG, "onClick on ${item.name} $checked ${video.title}")

                if(item.name == FAV){
                    scope.launch {
                        userViewModel.favorite(checked, video.id).collect{

                        }
                    }
                }

            }

            video.overview?.let {
                Text(text = it)
            }

        }
    }

    if(openDialog.value)
        ErrorDialog(openDialog, userViewModel.errorMessage){
            Log.w(TAG, "Dismissed!")
        }
}

@Composable
fun HeaderView(video: Video) {
    ConstraintLayout() {
        val (image, icon) = createRefs()

        GlideImage(imageModel = video.getBackImage(),
            contentDescription = video.title,
            contentScale = ContentScale.FillWidth,
            previewPlaceholder = R.drawable.backdrop,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )

        Image(painter = painterResource(
            id = R.drawable.ic_play_btn_circle
        ), contentDescription = "Play",
            modifier = Modifier
                .size(64.dp)
                .constrainAs(icon) {
//                            Center Item in Image
                    top.linkTo(image.top)
                    start.linkTo(image.start)
                    end.linkTo(image.end)
                    bottom.linkTo(image.bottom)
                }

        )
    }
}

@Composable
fun GenderView(genders: MutableState<String>, duration: MutableState<String>) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val(genres, time) = createRefs()
        Text(
            text = genders.value,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .constrainAs(genres) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(time.start)
                }
        )

        Text(
            text = duration.value,
            modifier =
            Modifier.constrainAs(time) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
fun ActionsView(onClick: (IconItem, Boolean) -> Unit){
    LazyRow(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        movieActions.forEach{ item ->
            item {
                var isChecked = remember { mutableStateOf(false) }
                IconToggleButton(checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = !isChecked.value
                        onClick(item, isChecked.value)
                    }) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(4.dp))) {

                        Row(
                            modifier = Modifier
                                .background(MaterialTheme.colors.primary)
                                .padding(12.dp, 8.dp, 12.dp, 8.dp)
                        ) {

                            Image(
                                painter = if (isChecked.value) painterResource(item.invertResId) else painterResource(
                                    item.resId
                                ),
                                contentDescription = item.name,
                                alignment = Alignment.CenterEnd
                            )

                            item.text?.let {
                                Text(
                                    text = it,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun VideoPreview(){
    AppTheme {
        VideoView(video = fakeMovie)
    }
}