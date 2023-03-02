package com.devcommop.joaquin.seceleaderboard.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.devcommop.joaquin.seceleaderboard.R

@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    dialogMessage: String,
    dialogPositiveText: String = "OK",
    onDialogPositiveButtonClicked: (() -> Unit)? = null,
    onDialogStateChange: ((Boolean) -> Unit)? = null,
    onDismissRequest: (() -> Unit)? = null,
) {
    val textPaddingAll = 24.dp
    val buttonPaddingAll = 8.dp
    val dialogShape = RoundedCornerShape(16.dp)
    var dialogState by remember {
        mutableStateOf(true)
    }

    if (dialogState) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
                dialogState = false
            },
            title = null,
            text = null,
            buttons = {
                Column{
                    Image(
                        painter = painterResource(R.drawable.ic_lock),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(Modifier.padding(all = textPaddingAll)){
                        Text(
                            text = dialogMessage
                        )
                    }
                    Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)

                    Row(
                        modifier = Modifier.padding(all = buttonPaddingAll),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                onDialogStateChange?.invoke(false)
                                onDialogPositiveButtonClicked?.invoke()
                            }
                        ) {
                            Text(text = dialogPositiveText, color = MaterialTheme.colors.onSurface)
                        }
                    }
                }

            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
            modifier = modifier,
            shape = dialogShape
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomDialog() {
    CustomDialog(dialogMessage = "Abba jabba dabba")
}