package com.szn.movie.auth.ui

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.szn.core.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.szn.movie.auth.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavHostController) {

    val userViewModel: UserViewModel = hiltViewModel()
    var login by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var pseudo by remember { mutableStateOf("") }
    var errorMessage = userViewModel.errorMessage


    val title = stringResource(id = R.string.account_create)
    Column(Modifier.padding(24.dp)) {

//        Titre
        Text(text = title,
            color = Color.Black)

        Spacer( Modifier.height(24.dp))
/*

//        firstname
        Text(text = stringResource(id = R.string.firstname))
        RoundedCornersTextField(holder = "Gérard",
            onValueChange = { login = it }
        )
        Spacer( Modifier.height(8.dp))

        Text(text = stringResource(id = R.string.lastname))
        RoundedCornersTextField(
            holder = "Depardieu",
            onValueChange = { pass = it }
        )

        Spacer( Modifier.height(8.dp))
*/

    //        pseudo
        Text(text = stringResource(id = R.string.nickname))
        RoundedCornersTextField(
            holder = "Gégé",
            onValueChange = { pseudo = it }
        )

        Spacer( Modifier.height(8.dp))
/*
    //        mail
        Text(text = stringResource(id = R.string.mail))
        RoundedCornersTextField(
            holder = stringResource(id = R.string.mail),
            onValueChange = { pseudo = it }
        )

        Spacer( Modifier.height(8.dp))*/

    //        Pass
        Text(text = stringResource(id = R.string.pass))
        RoundedCornersTextField(
            holder = stringResource(id = R.string.pass),
            onValueChange = { pass = it }
        )

        Spacer( Modifier.height(48.dp))

        Button(onClick = {
            Log.w("Login", "onButtonClick $login $pseudo")
            CoroutineScope(Dispatchers.Main).launch {
                userViewModel.login(login, pass, pseudo)
            }

            navController.navigate("home")

        }) {
            Text(text = stringResource(id = R.string.account_create),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth())
        }
    }

    errorMessage.value.let {
        Toast.makeText(navController.context, it, Toast.LENGTH_LONG).show()
    }

}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_TYPE_NORMAL)
fun PreviewLoginScreen(){
    LoginScreen(rememberNavController())
}

/**
 * TODO: extract
 */
@Composable
fun RoundedCornersTextField(holder: String, onValueChange: (String) -> Unit) {
    val state = remember {
        mutableStateOf("")
    }

    OutlinedTextField(value = state.value,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {
            state.value = it
            onValueChange.invoke(it)
        },
        singleLine = true,
        shape = RoundedCornerShape(4.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
//             textColor = Color.White,
//             placeholderColor = Color.White,
//             cursorColor = Color.Red,
            focusedBorderColor = Color.Black, // TODO: DarkMode
         ),
        placeholder = {
            Text(text = holder)
        }
        )
}