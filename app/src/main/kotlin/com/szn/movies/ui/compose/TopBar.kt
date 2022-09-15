package com.szn.movies.ui.compose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.szn.core.R
import com.szn.core.network.ApiStatus
import com.szn.movie.auth.viewmodel.UserViewModel
import com.szn.movies.ui.navigation.NavRoutes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TopBar(navController: NavHostController, canPop: Boolean, showLogout: Boolean, title: MutableState<String>) {

    val userViewModel: UserViewModel = hiltViewModel()
    val context = LocalContext.current
    Log.w("TopBar", "logged: ${userViewModel.isLogged}...")
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
            actions = {
                if (showLogout && userViewModel.isLogged.value){
                    IconButton(onClick = {

//                    openDialog.value = true
                        CoroutineScope(Dispatchers.Main).launch {

                            userViewModel.logout().collect{
                                Log.w("TopBar", "Logout $it ")
                                if(it.status == ApiStatus.SUCCESS){
                                    navController.navigate(NavRoutes.Splash.route)
                                } else if(it.status == ApiStatus.ERROR) {
                                    Toast.makeText(context, "Error while Logout ${it.message}", Toast.LENGTH_LONG).show()
                                }
                            }

                        }
                    }) {
                        Icon(
                            painterResource(id = R.drawable.ic_power_settings),
                            null,
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }

            },
            backgroundColor = MaterialTheme.colors.background,
        )
    }
}


@Preview
@Composable
fun TopBarPreview(){
    TopBar(rememberNavController(), false, true, mutableStateOf("Preview"))
}