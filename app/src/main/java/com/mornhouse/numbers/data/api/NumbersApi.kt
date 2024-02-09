package com.mornhouse.numbers.data.api

import com.mornhouse.numbers.data.model.NumberFactResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {
    @GET("{number}")
    suspend fun fetchNumberFact(@Path("number") number: Long): NumberFactResponse

    @GET("random/math")
    suspend fun fetchRandomNumberFact(): NumberFactResponse
}