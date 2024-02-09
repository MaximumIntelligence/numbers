package com.mornhouse.numbers.data.dataSource.remote.repository

import com.mornhouse.numbers.data.api.NumbersApi
import com.mornhouse.numbers.data.dataSource.local.db.dao.NumbersDao
import com.mornhouse.numbers.data.utils.mapper.DataMapper
import com.mornhouse.numbers.domain.repository.NumbersRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NumbersRepositoryImpl @Inject constructor(
    private val numbersApi: NumbersApi,
    private val numbersDao: NumbersDao,
) : NumbersRepository {
    override suspend fun fetchNumberFact(number: Long) {
        val response = numbersApi.fetchNumberFact(number = number)

        if (!response.isNumberFound) {
            throw Exception(response.text)
        } else {
            val entity = DataMapper.mapNumberFactResponseToNumberFactEntity(response)
            numbersDao.insertNumberEntity(entity)
        }
    }

    override suspend fun fetchRandomNumberFact() {
        val response = numbersApi.fetchRandomNumberFact()

        if (!response.isNumberFound) {
            throw Exception(response.text)
        } else {
            val entity = DataMapper.mapNumberFactResponseToNumberFactEntity(response)
            numbersDao.insertNumberEntity(entity)
        }
    }

    override fun observeNumbersFlow() =
        numbersDao.observeNumbersFactsFlow().map { numberFactEntityList ->
            numberFactEntityList?.map {
                DataMapper.mapNumberFactEntityToNumberFactModel(it)
            }
        }
}
