package com.mornhouse.numbers.data.utils.mapper

import com.mornhouse.numbers.data.dataSource.local.db.model.NumberFactEntity
import com.mornhouse.numbers.data.model.NumberFactResponse
import com.mornhouse.numbers.domain.model.NumberFactModel
import java.util.UUID

object DataMapper {
    fun mapNumberFactResponseToNumberFactEntity(response: NumberFactResponse) = with(response) {
        NumberFactEntity(
            id = UUID.randomUUID().toString(),
            text = text ?: "",
            number = number ?: 0,
            isNumberFound = isNumberFound,
            createdAtInMs = System.currentTimeMillis()
        )
    }

    fun mapNumberFactEntityToNumberFactModel(entity: NumberFactEntity) = with(entity) {
        NumberFactModel(
            text = text,
            number = number,
            isNumberFound = isNumberFound,
        )
    }
}
