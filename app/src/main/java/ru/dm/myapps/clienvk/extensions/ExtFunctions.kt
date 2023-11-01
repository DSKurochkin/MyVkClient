package ru.dm.myapps.clienvk.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

fun <T> Flow<T>.withFlow(anotherFlow: Flow<T>): Flow<T> {
    return merge(this, anotherFlow)
}