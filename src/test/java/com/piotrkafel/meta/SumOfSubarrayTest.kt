package com.piotrkafel.meta

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SumOfSubarrayTest {

    @Test
    fun test() {
        Assertions.assertTrue(SumOfSubarray.sumOfSubarray(arrayOf(1, 2, 3, 4, 5), 9))
        Assertions.assertTrue(SumOfSubarray.sumOfSubarray(arrayOf(1, 3, 2, 5, 7, 2), 14))
        Assertions.assertTrue(SumOfSubarray.sumOfSubarray(arrayOf(4, 3, 2, 7, 1, 2), 10))
        Assertions.assertFalse(SumOfSubarray.sumOfSubarray(arrayOf(4, 3, 2, 7, 1, 2), 11))
    }
}