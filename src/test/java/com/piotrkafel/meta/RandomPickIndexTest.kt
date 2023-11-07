package com.piotrkafel.meta

import com.piotrkafel.meta.RandomPickIndex.Companion.randomPickIndex
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RandomPickIndexTest {

    @Test
    fun test() {
        Assertions.assertTrue(
                listOf(2,5,6).contains(randomPickIndex(arrayOf(1, 1, 9, 2, 3, 9, 9), 9))
        )
    }
}