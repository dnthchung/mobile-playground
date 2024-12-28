package com.chungdoan.fundamentals.ranges

fun main() {
    val rang1 = 1..10
    println(rang1)
    for (num1:Int in rang1) print(num1)

    val rang2 = 1.rangeTo(10)
    println(rang2)
    println("kiểm tra gi trị có nằm trong range hay không :  ${8 in rang1}  ")
    println("kiểm tra gi trị không nằm trong range hay không :  ${18 !in rang1}  ")

    val rang3 = "abc".rangeTo("xyz")
    println("${"cde" in rang3}")
    println("${"CDE" in rang3}")
}