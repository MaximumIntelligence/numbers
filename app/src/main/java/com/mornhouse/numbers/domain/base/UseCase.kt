package com.mornhouse.numbers.domain.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

abstract class UseCase<in Params, out Type> {
    suspend operator fun invoke(params: Params): Result<Type> = withContext(Dispatchers.IO) {
        runCatching {
            execute(params)
        }.onFailure { it.printStackTrace() }
    }

    @Throws(Exception::class)
    protected abstract suspend fun execute(params: Params): Type
}

abstract class NoParamsUseCase<out Type> : UseCase<Unit, Type>() {
    suspend operator fun invoke() = invoke(Unit)
}

abstract class CompletableUseCase<in Params> : UseCase<Params, Unit>()
abstract class NoParamsCompletableUseCase : NoParamsUseCase<Unit>()

abstract class ObservableUseCase<in Params, out Type> {

    private val paramsFlow = MutableSharedFlow<Params>(replay = 1)

    suspend operator fun invoke(params: Params) {
        paramsFlow.emit(params)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun observe(): Flow<Type> = paramsFlow
        .flatMapLatest { execute(it) }
        .flowOn(Dispatchers.IO)

    @Throws(Exception::class)
    protected abstract suspend fun execute(params: Params): Flow<Type>
}

abstract class NoParamsObservableUseCase<out Type> : ObservableUseCase<Unit, Type>() {
    suspend operator fun invoke() = invoke(Unit)
}