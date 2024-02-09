package com.mornhouse.numbers.data.dataSource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mornhouse.numbers.data.dataSource.local.db.dao.NumbersDao
import com.mornhouse.numbers.data.dataSource.local.db.model.NumberFactEntity

@Database(
    entities = [NumberFactEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun numbersDao(): NumbersDao
}