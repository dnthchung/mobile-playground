package com.chungdoan.fundamentals.flow

fun main() {
    val weekDay = 2
    when (weekDay) {
        2 -> println("mon")
        3 -> println("tue")
        else -> println("invalid")
    }
    when (weekDay) {
        in 2..7 -> println("mon-sat")
        else -> println("invalid")

    }
}