package com.chungdoan.fundamentals.variables

fun main() {
    //số nguyên ko dấu
    //cần khai báo data type rõ ràng
    var unitNum: UInt
    unitNum = 2U
    println(unitNum)
    println(unitNum.javaClass)

    var num2 = 2u
    println(num2.javaClass)

    var uShort : UShort = 3u
    println(uShort.javaClass)
    println(uShort)

    var uLong :ULong = 3u
    uLong = uShort.toULong()
    println(uLong.javaClass)


}