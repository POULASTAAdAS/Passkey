package com.poulastaa.passekyapp.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AuthViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideCredentialManager(@ApplicationContext context: Context): CredentialManager =
        CredentialManager.create(context)

    @Provides
    @ViewModelScoped
    fun provideGson(): Gson {
        return Gson()
    }
}