package com.poulastaa.passekyapp.presentation.screen.auth.common

import android.graphics.ColorSpace
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.poulastaa.passekyapp.R
import com.poulastaa.passekyapp.ui.theme.PrimaryButtonColor
import com.poulastaa.passekyapp.ui.theme.PrimaryTextColor

@Composable
fun CustomButton(
    text: String,
    icon: Painter,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .size(52.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        color = PrimaryButtonColor
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(52.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(
                            width = 1.5.dp,
                            color = PrimaryButtonColor,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentScale = ContentScale.Inside
                )
                Column {
                    Text(
                        text = text,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = PrimaryTextColor
                    )
                }
            }
        }
    }
}

@Composable
fun ContinueButton(
    isLoading: Boolean,
    text: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .size(56.dp)
            .clickable(
                onClick = onClick
            ),
        shape = RoundedCornerShape(10.dp),
        color = PrimaryButtonColor
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading)
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 1.5.dp
                )
            else
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(5.dp),
                    color = PrimaryTextColor
                )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Column {
        CustomButton(
            text = "Continue With Google",
            icon = painterResource(id = R.drawable.google)
        ) { }

        Spacer(modifier = Modifier.height(20.dp))

        CustomButton(
            text = "Continue With Email",
            icon = painterResource(id = R.drawable.email)
        ) { }

        Spacer(modifier = Modifier.height(20.dp))

        ContinueButton(
            text = "Continue with Passkey",
            isLoading = true
        ) {

        }
    }
}