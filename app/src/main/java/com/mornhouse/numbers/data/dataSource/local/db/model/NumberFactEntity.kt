package com.mornhouse.numbers.data.dataSource.local.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Numbers")
data class NumberFactEntity(
    @PrimaryKey val id: String,
    val text: String,
    val number: Long,
    val isNumberFound: Boolean,
    val createdAtInMs: Long,
)
