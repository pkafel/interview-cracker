package com.piotrkafel.stripe

import java.util.ArrayDeque
import java.util.Deque

class ShopOpeningTimeProblem {

    class OpeningTime {
        internal var isClosed = false
        internal val customerPresence: StringBuilder = StringBuilder()
    }
    companion object {

        fun computePenalty(log: String, closingTime: Int): Int {
            val logArray = log.split(" ")
            var penalty = 0
            for (i in logArray.indices) {
                if (i < closingTime) {
                    if (logArray[i] == "N") penalty++
                } else {
                    if (logArray[i] == "Y") penalty++
                }
            }
            return penalty
        }

        fun findBestClosingTime(log: String): Int {
            var smallestPenalty = Int.MAX_VALUE
            var bestClosingTime = 0
            val logArray = log.split(" ")
            for (i in 0..logArray.size) {
                val penalty = computePenalty(log, i)
                if (penalty < smallestPenalty) {
                    smallestPenalty = penalty
                    bestClosingTime = i
                }
            }
            return bestClosingTime
        }

        // I assume here the order is defined by BEGIN statement.
        // In case we have to order by END it seems the task is easier.
        fun getAllClosingTimesOrderByBegin(log: String): List<Int> {
            val logParts: List<String> = log.split(" ")

            var indexOfCurrentPart = -1
            val correctOpeningTime: MutableList<OpeningTime> = ArrayList()
            for (part in logParts) {
                when (part) {
                    "BEGIN" -> {
                        correctOpeningTime.add(OpeningTime())
                        indexOfCurrentPart = correctOpeningTime.size - 1
                    }
                    "END" -> {
                        correctOpeningTime[indexOfCurrentPart].isClosed = true
                        indexOfCurrentPart = correctOpeningTime.indexOfLast { !it.isClosed }
                    }
                    else -> {
                        correctOpeningTime[indexOfCurrentPart].customerPresence.append(part).append(" ")
                    }
                }
            }
            return correctOpeningTime.map { findBestClosingTime(it.customerPresence.toString().trimEnd()) }
        }

        fun getAllClosingTimesOrderByEnd(log: String): List<Int> {
            val logParts: List<String> = log.split(" ")

            val result: MutableList<OpeningTime> = arrayListOf()
            val correctOpeningTime: Deque<OpeningTime> = ArrayDeque()
            for (part in logParts) {
                when (part) {
                    "BEGIN" -> {
                        correctOpeningTime.push(OpeningTime())
                    }
                    "END" -> {
                        val currentOpeningTime: OpeningTime  = correctOpeningTime.poll()
                        result.add(currentOpeningTime)
                    }
                    else -> {
                        correctOpeningTime.peek().customerPresence.append(part).append(" ")
                    }
                }
            }
            return result.map { findBestClosingTime(it.customerPresence.toString().trimEnd()) }
        }
    }
}
