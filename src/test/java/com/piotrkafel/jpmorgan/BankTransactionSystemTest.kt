package com.piotrkafel.jpmorgan

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BankTransactionSystemTest {

    @Test
    fun test() {
        val input = listOf(
                "OPEN 10/09/2023 100 $1000",
                "OPEN 10/10/2023 101 $2000",
                "PAY 10/11/2023 100 $500",
                "TRANSFER 10/12/2023 100 101 $300",
                "BALANCE 10/13/2023 101",
                "BALANCE 10/14/2023 100",
                "PAY 10/15/2023 101 $100",
                "BALANCE 10/18/2023 101"
        )

        val result = BankTransactionSystem().processCommands(input)

        Assertions.assertEquals(listOf(2300, 215, 2203), result)
    }
}