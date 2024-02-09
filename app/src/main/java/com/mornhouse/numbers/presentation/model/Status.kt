package com.mornhouse.numbers.presentation.model

sealed class Status {
    object Loading : Status()
    object Success : Status()
    class Failure(val error: Throwable) : Status()
}