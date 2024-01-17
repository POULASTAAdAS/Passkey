package com.poulastaa.passekyapp.presentation.screen.auth

import androidx.lifecycle.ViewModel
import com.poulastaa.passekyapp.domain.repository.PasskeyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val api: PasskeyRepository
): ViewModel() {

}