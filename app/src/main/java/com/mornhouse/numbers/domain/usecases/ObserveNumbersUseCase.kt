package com.mornhouse.numbers.domain.usecases

import com.mornhouse.numbers.domain.base.NoParamsObservableUseCase
import com.mornhouse.numbers.domain.model.NumberFactModel
import com.mornhouse.numbers.domain.repository.NumbersRepository
import javax.inject.Inject

class ObserveNumbersUseCase @Inject constructor(
    private val numbersRepository: NumbersRepository,
) : NoParamsObservableUseCase<List<NumberFactModel>?>() {
    override suspend fun execute(params: Unit) = numbersRepository.observeNumbersFlow()
}