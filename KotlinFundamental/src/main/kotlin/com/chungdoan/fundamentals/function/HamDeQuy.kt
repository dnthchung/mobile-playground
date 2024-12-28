package com.chungdoan.fundamentals.function

//recursive function

fun fact(n:Int) : Int {
    var f = 1
    for(value in 1..n){
        f *= value
    }
    return f

}

// de quy
fun factorial(n:Int) : Int {
    if(n == 1)return 1

    return n* factorial(n-1)
}

fun main() {
    var myrs = fact(5)
    println(myrs)
}