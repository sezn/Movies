package com.szn.movie.auth.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.szn.movie.auth.R
import com.szn.movie.auth.ui.dialogs.ErrorDialog
import com.szn.movie.auth.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavHostController) {

    val userViewModel: UserViewModel = hiltViewModel()
    var login by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var pseudo by remember { mutableStateOf("") }
    val logged = remember { userViewModel.isLogged}
    val openDialog = remember { userViewModel.showError}
    var errorMessage = userViewModel.errorMessage
    val title = stringResource(id = R.string.account_login)
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val (focusRequester) = remember{ FocusRequester.createRefs() }


    if(logged.value)
        navController.navigate("home"){
            launchSingleTop = true
        }

    Image(painter = painterResource(id = com.szn.common.R.drawable.background),
        contentDescription = "BG",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize(),
        colorFilter = ColorFilter.tint(
            Color(0x1A040722),
            BlendMode.Saturation
        )
    )
    Column(Modifier.padding(24.dp)) {

//        Titre
        Text(text = title,
            style = MaterialTheme.typography.h2)

        Spacer( Modifier.height(24.dp))

//        pseudo
        Text(text = stringResource(id = R.string.nickname))
        RoundedCornersTextField(
            holder = stringResource(id = R.string.friendly_nickname),
            onValueChange = { pseudo = it },
            focusRequester = focusRequester
        )

        Spacer( Modifier.height(8.dp))

//        Pass
        Text(text = stringResource(id = R.string.pass))
        RoundedCornersTextField(
            holder = stringResource(id = R.string.pass),
            onValueChange = { pass = it },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            focusRequester = focusRequester
        ) {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            val description = if (passwordVisible) stringResource(id = com.szn.movie.auth.R.string.pass_hide) else stringResource(id = com.szn.movie.auth.R.string.pass_show)

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        }

        Spacer( Modifier.height(48.dp))

//       Button
        Button(onClick = {
            Log.w("Login", "onButtonClick $login $pseudo")
            scope.launch {
                userViewModel.login(pass, pseudo)
            }
        },
            modifier = Modifier
                .focusRequester(focusRequester)
                .border(0.75.dp, MaterialTheme.colors.onBackground, RoundedCornerShape(4.dp))

        ) {
            Text(text = stringResource(id = R.string.account_login),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth())
        }
    }

    if(openDialog.value)
        ErrorDialog(openDialog, errorMessage){}
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
fun RoundedCornersTextField(
    holder: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    focusRequester: FocusRequester,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val state = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = state.value,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .focusRequester(focusRequester),
        onValueChange = {
            state.value = it
            onValueChange.invoke(it)
        },
        singleLine = true,
        shape = RoundedCornerShape(4.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
//             textColor = Color.White,
//             placeholderColor = Color.White,
            cursorColor = Color.White,
//            focusedBorderColor = Color.Black, // TODO: DarkMode
        ),
        placeholder = {
            Text(text = holder)
        },
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,

        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = {
                focusRequester.requestFocus()
            },
        )
    )
}