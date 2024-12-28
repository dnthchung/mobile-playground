package com.chungdoan.fundamentals.function

fun main() {
    val display2 = {a : Int -> println("result is $a") }
    add(2,3, display2)
    render("chungdt")
}
fun add(a:Int , b:Int, display: (Int) -> Unit ) : Int {
    var result = a + b
    display(result)
    return result
}
fun render(name : String) : Unit {
    println("Hello $name")
    if(true) return
    println("say bye $name")
}
