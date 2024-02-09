package com.mornhouse.numbers.presentation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mornhouse.numbers.domain.usecases.FetchFactAboutRandomNumberUseCase
import com.mornhouse.numbers.domain.usecases.FetchFactAboutUserNumberUseCase
import com.mornhouse.numbers.domain.usecases.ObserveNumbersUseCase
import com.mornhouse.numbers.presentation.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchFactAboutUserNumberUseCase: FetchFactAboutUserNumberUseCase,
    private val fetchFactAboutRandomNumberUseCase: FetchFactAboutRandomNumberUseCase,
    private val observeNumbersUseCase: ObserveNumbersUseCase,
) : ViewModel() {
    val observeNumbersFlow = observeNumbersUseCase.observe()

    init {
        viewModelScope.launch {
            observeNumbersUseCase()
        }
    }

    private val _fetchNumberFactStatusFlow = MutableSharedFlow<Status>()
    val fetchNumberFactStatusFlow: Flow<Status> = _fetchNumberFactStatusFlow

    fun fetchFactAboutNumber(number: Long) {
        viewModelScope.launch {
            _fetchNumberFactStatusFlow.emit(Status.Loading)
            val params = FetchFactAboutUserNumberUseCase.Params(number)
            fetchFactAboutUserNumberUseCase(params).onSuccess {
                _fetchNumberFactStatusFlow.emit(Status.Success)
            }.onFailure {
                _fetchNumberFactStatusFlow.emit(Status.Failure(it))
            }
        }
    }

    fun fetchFactAboutRandomNumber() {
        viewModelScope.launch {
            _fetchNumberFactStatusFlow.emit(Status.Loading)
            fetchFactAboutRandomNumberUseCase().onSuccess {
                _fetchNumberFactStatusFlow.emit(Status.Success)
            }.onFailure {
                _fetchNumberFactStatusFlow.emit(Status.Failure(it))
            }
        }
    }
}