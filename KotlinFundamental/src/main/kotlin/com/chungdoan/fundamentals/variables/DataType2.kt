package com.chungdoan.fundamentals.variables

fun main() {
    var shortNum: Short = 8
    shortNum = 20

    var byteNum: Byte = 2
    byteNum = 20

    println(shortNum.javaClass)
    println(byteNum.javaClass)

    byteNum = shortNum.toByte()
}