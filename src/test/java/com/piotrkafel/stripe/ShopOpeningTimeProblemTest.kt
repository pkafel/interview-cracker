package com.piotrkafel.stripe

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ShopOpeningTimeProblemTest {
    @Test
    fun shouldCalculatePenaltyCorrectly() {
        Assertions.assertEquals(3, ShopOpeningTimeProblem.computePenalty("Y Y Y N N N N", 0))
        Assertions.assertEquals(4, ShopOpeningTimeProblem.computePenalty("Y Y Y N N N N", 7))
        Assertions.assertEquals(0, ShopOpeningTimeProblem.computePenalty("Y Y Y N N N N", 3))
        Assertions.assertEquals(0, ShopOpeningTimeProblem.computePenalty("", 0))
        Assertions.assertEquals(1, ShopOpeningTimeProblem.computePenalty("Y N Y N N N N", 3))
    }

    @Test
    fun shouldFindBestClosingTimeCorrectly() {
        Assertions.assertEquals(3, ShopOpeningTimeProblem.findBestClosingTime("Y Y Y N N N N"))
        Assertions.assertEquals(0, ShopOpeningTimeProblem.findBestClosingTime(""))
        Assertions.assertEquals(1, ShopOpeningTimeProblem.findBestClosingTime("Y"))
        Assertions.assertEquals(0, ShopOpeningTimeProblem.findBestClosingTime("N N N N"))
        Assertions.assertEquals(4, ShopOpeningTimeProblem.findBestClosingTime("Y Y Y Y"))
        Assertions.assertEquals(5, ShopOpeningTimeProblem.findBestClosingTime("N Y Y Y Y N N N Y N N Y Y N N N N Y Y N N Y N N N"))
        Assertions.assertEquals(0, ShopOpeningTimeProblem.findBestClosingTime("N N N N N Y Y Y N N N N Y Y Y N N N Y N Y Y N Y N"))
        Assertions.assertEquals(25, ShopOpeningTimeProblem.findBestClosingTime("Y Y N N N Y Y N Y Y N N N Y Y N N Y Y Y N Y N Y Y"))
    }

    @Test
    fun shouldGetAllBestClosingTimeByBeginCorrectly() {
        Assertions.assertEquals(listOf(2, 0), ShopOpeningTimeProblem.getAllClosingTimesOrderByBegin("BEGIN Y Y END BEGIN N N END"))
        Assertions.assertEquals(listOf(1, 2, 2), ShopOpeningTimeProblem.getAllClosingTimesOrderByBegin("BEGIN BEGIN BEGIN Y Y N Y END Y Y N N END Y N Y N END"))
        Assertions.assertEquals(listOf(1, 0, 1), ShopOpeningTimeProblem.getAllClosingTimesOrderByBegin("BEGIN Y N BEGIN N N BEGIN Y N END Y END Y END"))
        Assertions.assertEquals(listOf(1, 0), ShopOpeningTimeProblem.getAllClosingTimesOrderByBegin("BEGIN Y N END BEGIN N N Y END"))
    }

    @Test
    fun shouldGetAllBestClosingTimeByEndCorrectly() {
        Assertions.assertEquals(listOf(2, 0), ShopOpeningTimeProblem.getAllClosingTimesOrderByEnd("BEGIN Y Y END BEGIN N N END"))
        Assertions.assertEquals(listOf(2, 2, 1), ShopOpeningTimeProblem.getAllClosingTimesOrderByEnd("BEGIN BEGIN BEGIN Y Y N Y END Y Y N N END Y N Y N END"))
        Assertions.assertEquals(listOf(1, 0, 1), ShopOpeningTimeProblem.getAllClosingTimesOrderByEnd("BEGIN Y N BEGIN N N BEGIN Y N END Y END Y END"))
        Assertions.assertEquals(listOf(1, 0), ShopOpeningTimeProblem.getAllClosingTimesOrderByEnd("BEGIN Y N END BEGIN N N Y END"))
    }
}
