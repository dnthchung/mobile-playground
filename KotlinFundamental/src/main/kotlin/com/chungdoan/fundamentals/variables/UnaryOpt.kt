package com.chungdoan.fundamentals.variables

fun main() {
    var a = 5
    var b = 6


    println("$a = ${-a}")
    println("$a = ${+a}")

    // ++ và -- thì làm thay đổi giá trị biến nên dùng với var
    println("$a = ${++a}")
    println(a)
    println("$a = ${--a}")

    a = 5
    b = 6
    b = b + (++a)
    println("$a va $b")
    //a = a + 1
    //b = b + a

    a = 5
    b = 6
    b = b + (a++)
    println("$a va $b")
    //b = b + a
    //a = a + 1
}