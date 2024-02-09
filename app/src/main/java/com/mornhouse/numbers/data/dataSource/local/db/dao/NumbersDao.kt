package com.mornhouse.numbers.data.dataSource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mornhouse.numbers.data.dataSource.local.db.model.NumberFactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NumbersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNumberEntity(entity: NumberFactEntity)

    @Query(
        """
        SELECT * FROM Numbers
        ORDER BY createdAtInMs DESC
        """
    )
    fun observeNumbersFactsFlow(): Flow<List<NumberFactEntity>?>
}