package com.poulastaa.passekyapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.poulastaa.passekyapp.data.remote.PasskeyApi
import com.poulastaa.passekyapp.data.repository.PasskeyRepositoryImpl
import com.poulastaa.passekyapp.domain.repository.PasskeyRepository
import com.poulastaa.passekyapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun providePasskeyApi(retrofit: Retrofit): PasskeyApi = retrofit.create(PasskeyApi::class.java)

    @Provides
    @Singleton
    fun providePasskeyRepository(passkeyApi: PasskeyApi): PasskeyRepository =
        PasskeyRepositoryImpl(api = passkeyApi)
}