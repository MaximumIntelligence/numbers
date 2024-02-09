package com.mornhouse.numbers.data.di

import android.content.Context
import androidx.room.Room
import com.mornhouse.numbers.data.dataSource.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "Application database")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideNumbersDao(appDatabase: AppDatabase) = appDatabase.numbersDao()
}