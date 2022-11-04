package com.szn.movie.auth.ui.dialogs

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

/**
 * TODO: extract in common-ui?
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ErrorDialog(openDialog: MutableState<Boolean>,
                message: MutableState<String>,
                onDismiss: () -> Unit) {

    Dialog(
        onDismissRequest = {
            onDismiss.invoke()
            openDialog.value = false
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            elevation = 32.dp,
            //MEF: Need ExperimentalMaterialApi
            onClick = {
                openDialog.value = false
                onDismiss.invoke()
            }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(MaterialTheme.colors.onError)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(color = MaterialTheme.colors.error),
                        contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(96.dp),
                        painter = painterResource(id = com.szn.common.R.drawable.ic_error),
                        contentDescription = "Error",
                        alignment = Alignment.Center
                    )
                }

                Text(
                    modifier = Modifier.padding(24.dp),
                    text = message.value,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.error,
                    style = TextStyle(
                        fontWeight = FontWeight.Black,
                        fontSize = 24.sp,
                        letterSpacing = 1.5.sp
                    )
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp, start = 36.dp, end = 36.dp, bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error),
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text(
                        text = stringResource(id = com.szn.common.R.string.ok),
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ErrorDialogPreview(){
        ErrorDialog(openDialog = mutableStateOf(true),
            message = mutableStateOf("Erreur prévisualisation")){}
}