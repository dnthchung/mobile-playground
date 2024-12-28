package com.chungdoan.fundamentals.flow

fun main() {
//    val weekDay = 2
//    val mess = when (weekDay) {
//        2 -> "mon"
//        3 -> "tue"
//        else -> println("invalid")
//    }
//    println(mess)

    val value: Any = "Hello"
    val mess = when (value) {
        is String -> value.length
        is Int -> value
        else -> 0
    }
    println(mess)

}