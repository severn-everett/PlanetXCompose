package com.severett.planetxcompose.common.model

import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.locks.reentrantLock
import kotlinx.atomicfu.locks.withLock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private const val LIMIT = 10_000

fun runRace(): Pair<Int, Int> {
    val safeCounter = atomic(0)
    var unsafeCounter = 0
    runBlocking {
        (0 until LIMIT).map {
            CoroutineScope(Dispatchers.Default).launch {
                safeCounter += 1
                unsafeCounter += 1
            }
        }.forEach { it.join() }
    }
    return safeCounter.value to unsafeCounter
}

fun lockDemo(): Int {
    var unsafeValue = 0
    val lock = reentrantLock()
    runBlocking {
        (0 until LIMIT).map {
            CoroutineScope(Dispatchers.Default).launch {
                lock.withLock { unsafeValue += 1 }
            }
        }.forEach { it.join() }
    }
    return unsafeValue
}
