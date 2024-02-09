package com.mornhouse.numbers.data.di

import com.mornhouse.numbers.data.dataSource.remote.repository.NumbersRepositoryImpl
import com.mornhouse.numbers.domain.repository.NumbersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindNumbersRepository(numbersRepositoryImpl: NumbersRepositoryImpl): NumbersRepository
}