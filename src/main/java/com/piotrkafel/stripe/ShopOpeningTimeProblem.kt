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

        fun getAllClosingTimesOrderByBegin(log: String): List<Int> {
            val logParts: List<String> = log.split(" ")

            var indexOfCurrentPart = -1
            val correctOpeningTimes: MutableList<OpeningTime> = ArrayList()
            for (part in logParts) {
                when (part) {
                    "BEGIN" -> { // when BEGIN create new opening time and update index to point to current window
                        correctOpeningTimes.add(OpeningTime())
                        indexOfCurrentPart = correctOpeningTimes.size - 1
                    }
                    "END" -> {  // when END mark the current window as closed and find last not closed window
                        correctOpeningTimes[indexOfCurrentPart].isClosed = true
                        indexOfCurrentPart = correctOpeningTimes.indexOfLast { !it.isClosed }
                    }
                    else -> {   // when part of opening time just add it to current window
                        correctOpeningTimes[indexOfCurrentPart].customerPresence.append(part).append(" ")
                    }
                }
            }
            return correctOpeningTimes.map { findBestClosingTime(it.customerPresence.toString().trimEnd()) }
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
