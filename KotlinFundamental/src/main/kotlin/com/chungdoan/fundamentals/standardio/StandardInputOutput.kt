package com.chungdoan.fundamentals.standardio

import java.util.Scanner

fun main() {
    //readln return string not null, readline return both string and null
    print("enter ur name: ")
    val name = readln()
    println("ur name is $name")

    print("enter ur salary: ")
    val salary = readLine() ?.toInt()
    salary
    println("ur salary : $salary ")

    val scanner = Scanner(System.`in`)
    print("Enter age: ")
    val age = scanner.nextInt()
    println("ur age: $age")
}