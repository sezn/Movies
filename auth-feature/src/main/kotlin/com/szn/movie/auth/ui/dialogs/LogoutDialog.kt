package com.szn.movie.auth.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.szn.movie.auth.R

/**
 * TODO: extract in common-ui?
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LogoutDialog(
    openDialog: MutableState<Boolean>,
    onLogout: () -> Unit,
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
                modifier = Modifier.background(MaterialTheme.colors.background)
            ) {

                Text(
                    modifier = Modifier.padding(24.dp),
                    text = stringResource(id = R.string.logout_confirm),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.error,
                    style = TextStyle(
                        fontWeight = FontWeight.Black,
                        fontSize = 24.sp,
                        letterSpacing = 1.2.sp
                    )
                )

                Row(
                    Modifier.fillMaxWidth().padding(0.dp, 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly) {

                    TextButton(onClick = {
                        onDismiss.invoke()
                        openDialog.value = false
                    },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors()
                        ) {

                        androidx.compose.material3.Text(
                            text = stringResource(id = R.string.nok),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(5.dp).width(96.dp)
                        )
                    }
                    TextButton(onClick = {
                        onLogout.invoke()
                        openDialog.value = false
                    },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors()
                    ) {
                        androidx.compose.material3.Text(
                            text = stringResource(id = R.string.ok),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraBold,
//                            color = Color.Black,
                            modifier = Modifier.padding(5.dp).width(96.dp)
                        )
                    }
                }


            }
        }
    }
}

@Preview
@Composable
fun LogoutDialogPreview(){
    LogoutDialog(openDialog = mutableStateOf(true), {}, {})
}