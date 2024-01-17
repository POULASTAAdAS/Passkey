package com.poulastaa.passekyapp.presentation.screen.auth

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.poulastaa.passekyapp.R
import com.poulastaa.passekyapp.data.model.AuthState
import com.poulastaa.passekyapp.navigation.Screens
import com.poulastaa.passekyapp.presentation.screen.auth.common.ContinueButton
import com.poulastaa.passekyapp.presentation.screen.auth.common.CustomButton
import com.poulastaa.passekyapp.presentation.screen.auth.common.CustomTextField

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigate: (String) -> Unit
) {
    val uiState = viewModel.state.collectAsState(initial = AuthState.Default).value

    val email by viewModel.email
    val isLoading by viewModel.isLoading

    val context = LocalContext.current as Activity

    LaunchedEffect(key1 = uiState) { // navigate to screen
        when (uiState) {
            is AuthState.Default -> Unit
            is AuthState.SignInFailure -> {
                Log.e("SignInFailure", uiState.message)
            }

            is AuthState.SignInSuccess -> {
                navigate(Screens.Home.route)
            }

            is AuthState.SignUpFailure -> {
                Log.e("SignUpFailure", uiState.message)
            }

            is AuthState.SignUpSuccess -> {
                navigate(Screens.Home.route)
            }

            is AuthState.NotValidEmail -> {
                Log.e("NotValidEmail", "input valid email")
            }
        }
    }

    Scaffold(
        containerColor = Color.White
    ) {
        AuthScreenContent(
            paddingValues = it,
            isLoading = isLoading,
            value = email,
            onValueChange = viewModel::onValueChange,
            onDone = { viewModel.onDone(context) }
        )
    }
}


@Composable
fun AuthScreenContent(
    paddingValues: PaddingValues,
    isLoading: Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding() + 25.dp,
                start = 25.dp,
                end = 25.dp
            )
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.weight(7f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.aspectRatio(2f)
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomTextField(
                value = value,
                done = {
                    focusManager.clearFocus()
                    onDone.invoke()
                },
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(25.dp))

            ContinueButton(
                isLoading = isLoading,
                text = "Continue With passkey",
                onClick = {
                    focusManager.clearFocus()
                    onDone.invoke()
                }
            )
        }

        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.Bottom
        ) {
            CustomButton(
                text = "Continue With Google",
                icon = painterResource(id = R.drawable.google),
                onClick = {}
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(
                text = "Continue With Email",
                icon = painterResource(id = R.drawable.email),
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AuthScreenContent(
        paddingValues = PaddingValues(), value = "",
        onValueChange = {}, isLoading = false
    ) {

    }
}