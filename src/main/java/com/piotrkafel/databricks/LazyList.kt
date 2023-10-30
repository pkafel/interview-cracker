package com.piotrkafel.databricks

import java.util.function.Function

// WIP
class LazyList<E> {

    val storage: List<E> = listOf()

    fun <I, O>  map(function: Function<I, O>): LazyList<O> {
        return LazyList()
    }

    fun indexOf(item: Any): Int {
        return 0
    }
}