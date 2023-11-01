package com.piotrkafel.databricks

interface LazyList<E> {

    fun <O>  map(function: (E) -> O): LazyList<O>

    fun indexOf(item: E): Int
}

class LazyListImpl<E> private constructor(private val items: () -> List<E>): LazyList<E> {

    companion object {
        @JvmStatic
        fun <E> of(items: List<E>): LazyList<E> = LazyListImpl { items }
    }

    override fun <O> map(function: (E) -> O): LazyList<O> = LazyListImpl { items.invoke().map(function) }

    override fun indexOf(item: E): Int = items.invoke().indexOf(item)
}
