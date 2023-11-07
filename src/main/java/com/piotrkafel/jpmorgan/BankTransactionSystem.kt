package com.piotrkafel.jpmorgan

import java.lang.RuntimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class BankTransactionSystem {

    private val dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy")

    private var accountsStorage = mutableMapOf<String, Account>()

    private data class Payment(val amount: Int, val date: LocalDate)

    private data class Account(var balance: Int, var payments: MutableList<Payment>)

    fun processCommands(commands: List<String>): List<Int> {
        val result = mutableListOf<Int>()
        for (command in commands) {
            val commandParts = command.split(" ")

            when (commandParts.first()) {
                "OPEN" -> {
                    val accountId = commandParts[2]
                    val balance = commandParts[3].substring(1).toInt()
                    accountsStorage[accountId] = Account(balance, mutableListOf())
                }
                "PAY" -> {
                    val from = commandParts[2]
                    val amount = commandParts[3].substring(1).toInt()
                    val date = LocalDate.parse(commandParts[1], dateFormat)
                    accountsStorage[from]!!.balance -= amount
                    accountsStorage[from]!!.payments.add(Payment(amount, date))
                }
                "TRANSFER" -> {
                    val from = commandParts[2]
                    val to = commandParts[3]
                    val amount = commandParts[4].substring(1).toInt()
                    accountsStorage[from]!!.balance -= amount
                    accountsStorage[to]!!.balance += amount
                }
                "BALANCE" -> {
                    val date = LocalDate.parse(commandParts[1], dateFormat)
                    val accountId = commandParts[2]
                    val account = accountsStorage[accountId]!!
                    for(payment in account.payments) {
                        if(payment.date.plusDays(2).isBefore(date)
                                or payment.date.plusDays(2).equals(date)) {
                            account.balance += (payment.amount * 0.03).roundToInt()
                        }
                    }

                    result.add(account.balance)
                }
                else -> {
                    throw RuntimeException("Unknown command")
                }
            }
        }
        return result
    }
}