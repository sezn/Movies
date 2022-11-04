package com.szn.movies.ui.compose.components

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.szn.common.R
import com.szn.movie.auth.ui.dialogs.LogoutDialog
import com.szn.movie.auth.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    navController: NavHostController,
    scope: CoroutineScope,
    canPop: Boolean,
    showLogout: Boolean,
    title: MutableState<String>
) {

    val userViewModel: UserViewModel = hiltViewModel()
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }

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
                // Just show it on Home for the moment
                if (showLogout && userViewModel.isLogged.value){
                    IconButton(onClick = {
                        openDialog.value = true
//                        navController.navigate(NavRoutes.Account.route)
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

    if(openDialog.value) {
        LogoutDialog(openDialog = openDialog, {
            Log.w("TopBar", "logout!!!!")
            logout(navController, scope, userViewModel)
        }, {
            Log.w("TopBar", "dismissed")
        })
    }

}

fun logout(navController: NavHostController, scope: CoroutineScope, userViewModel: UserViewModel){
    Log.w("TopBar", "Logout")
    scope.launch {
        userViewModel.logout().collect{
            Log.w("TopBar", "Logout $it ")
           /* if(it.status == ApiStatus.SUCCESS){
                navController.navigate(NavRoutes.Splash.route)
            } else if(it.status == ApiStatus.ERROR) {
                Toast.makeText(navController.context, "Error while Logout ${it.message}", Toast.LENGTH_LONG).show()
            }*/
        }
    }
}

@Preview
@Composable
fun TopBarPreview(){
    val title = remember { mutableStateOf("Preview") }
    TopBar(rememberNavController(), rememberCoroutineScope(), false, true, title)
}