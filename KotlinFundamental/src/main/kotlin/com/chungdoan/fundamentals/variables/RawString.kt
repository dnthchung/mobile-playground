package com.chungdoan.fundamentals.variables

fun main() {
    val st = "First Line" +
            "second line" +
            "Third line" +
            "Fourth line"

    println(st)

    val rawstring = """ 
        
    #    First line
    #    second
    #    third
    #    fourth
    """.trimMargin("#")
    println(rawstring)
}