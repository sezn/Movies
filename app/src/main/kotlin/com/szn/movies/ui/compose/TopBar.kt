package com.szn.movies.ui.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TopBar(navController: NavHostController, canPop: Boolean, title: MutableState<String>) {

    if(canPop){
        TopAppBar(
            navigationIcon = {
                if(canPop){
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) { Icon(imageVector = Icons.Filled.ArrowBack, null, tint = MaterialTheme.colors.onBackground) }
                } else
                    Spacer(modifier = Modifier.width(1.dp))
            },
            title = {
                Text(title.value,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.fillMaxWidth())
            },
            backgroundColor = MaterialTheme.colors.background
        )
    } else {
        TopAppBar(
            title = {
                Text(
                    title.value,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            backgroundColor = MaterialTheme.colors.background,
        )
    }
}
