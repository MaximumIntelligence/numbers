package com.mornhouse.numbers.domain.usecases

import com.mornhouse.numbers.domain.base.NoParamsCompletableUseCase
import com.mornhouse.numbers.domain.repository.NumbersRepository
import javax.inject.Inject

class FetchFactAboutRandomNumberUseCase @Inject constructor(
    private val numbersRepository: NumbersRepository,
) : NoParamsCompletableUseCase() {
    override suspend fun execute(params: Unit) = numbersRepository.fetchRandomNumberFact()
}