package com.szn.movies.account.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.szn.core.R
import com.szn.core.repos.UserRepo.Companion.userDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AccountView() {
    val userDataStore = LocalContext.current.userDataStore

//    val userViewModel: UserViewModel = hiltViewModel()
    CoroutineScope(Dispatchers.Main).launch {
        userDataStore.data.collect{
            Log.w("Account", "DataStore: $it ")
        }
    }
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