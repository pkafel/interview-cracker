package com.piotrkafel.meta

class SumOfSubarray {

    companion object {

        fun sumOfSubarray(input: Array<Int>, target: Int): Boolean {
            var startIndex = 0
            var currentSum = input[0]
            for(i in 1 until input.size) {
                currentSum += input[i]

                while(currentSum > target) {
                    currentSum -= input[startIndex]
                    startIndex++
                }

                if(currentSum == target) return true
            }
            return false
        }
    }
}