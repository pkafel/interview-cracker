package com.piotrkafel.meta

import kotlin.random.Random.Default.nextInt

class RandomPickIndex {

    companion object {

        fun randomPickIndex(input: Array<Int>, target: Int): Int {
            val indexes = mutableListOf<Int>()
            for(i in input.indices) {
                if(input[i] == target) indexes.add(i)
            }
            return indexes[nextInt(indexes.size)]
        }
    }
}