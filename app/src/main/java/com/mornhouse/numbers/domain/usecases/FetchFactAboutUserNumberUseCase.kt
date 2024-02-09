package com.mornhouse.numbers.domain.usecases

import com.mornhouse.numbers.domain.base.CompletableUseCase
import com.mornhouse.numbers.domain.repository.NumbersRepository
import javax.inject.Inject

class FetchFactAboutUserNumberUseCase @Inject constructor(
    private val numbersRepository: NumbersRepository,
) : CompletableUseCase<FetchFactAboutUserNumberUseCase.Params>() {
    override suspend fun execute(params: Params) =
        numbersRepository.fetchNumberFact(params.userNumber)

    data class Params(
        val userNumber: Long,
    )
}