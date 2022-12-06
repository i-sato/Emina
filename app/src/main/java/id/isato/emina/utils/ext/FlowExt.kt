package id.isato.emina.utils.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

fun <R> Flow<R>.asStateFlow(coroutineScope: CoroutineScope, initialValue: R) =
    stateIn(coroutineScope, SharingStarted.WhileSubscribed(), initialValue)