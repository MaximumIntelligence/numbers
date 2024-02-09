package com.mornhouse.numbers.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mornhouse.numbers.BuildConfig
import com.mornhouse.numbers.data.api.NumbersApi
import com.mornhouse.numbers.data.utils.ApiConstants.MEDIA_TYPE_JSON
import com.mornhouse.numbers.data.utils.NetworkRequestsInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(json.asConverterFactory(MEDIA_TYPE_JSON.toMediaType()))
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: NetworkRequestsInterceptor) = OkHttpClient
        .Builder().apply {
            addInterceptor(interceptor)
            addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    },
            )
        }.build()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    @Provides
    @Singleton
    fun provideNumbersApi(retrofit: Retrofit): NumbersApi = retrofit.create()
}