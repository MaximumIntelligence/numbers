package com.mornhouse.numbers.domain.repository

import com.mornhouse.numbers.domain.model.NumberFactModel
import kotlinx.coroutines.flow.Flow

interface NumbersRepository {
    suspend fun fetchNumberFact(number: Long)

    suspend fun fetchRandomNumberFact()

    fun observeNumbersFlow(): Flow<List<NumberFactModel>?>
}