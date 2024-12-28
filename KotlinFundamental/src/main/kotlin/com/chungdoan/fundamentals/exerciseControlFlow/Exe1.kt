package com.chungdoan.fundamentals.exerciseControlFlow

import java.util.*

fun main() {
val gpa = read()
}

fun read() : Float {
    val scanner = Scanner(System.`in`)
    var gpa : Float
    do {
       print("Enter GPA: ")
       gpa = scanner.nextFloat();
       if(gpa < 0 || gpa > 10)  {
           print{"invalid gpa, try again!. Press enter to continue."}
           readln()
       }
    }while (gpa < 0 || gpa > 10);
    return gpa

}