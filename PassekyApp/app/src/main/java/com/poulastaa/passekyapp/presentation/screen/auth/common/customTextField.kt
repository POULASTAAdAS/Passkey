package com.poulastaa.passekyapp.presentation.screen.auth.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.poulastaa.passekyapp.ui.theme.PrimaryButtonColor

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    done: () -> Unit,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = null
            )
        },
        label = {
            Text(text = "Email")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                done.invoke()
            }
        ),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = PrimaryButtonColor,
            focusedLabelColor = PrimaryButtonColor,
            focusedTrailingIconColor = PrimaryButtonColor,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            focusedIndicatorColor = PrimaryButtonColor
        )
    )
}

@Preview
@Composable
private fun Preview() {
    CustomTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        onValueChange = {},
        done = {}
    )
}