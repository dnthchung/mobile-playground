package com.chungdoan.fundamentals.function



fun sub(a:Int,b:Int) : Int = a-b
fun sub(a:Int,b:Int,c:Int) : Int = a-b-c
fun sub(a:Int,b:Int, c:Int,d:Int) : Int = a-b-c-d
fun sub(a:Int,b:Int, c:Int,d:Int,e:Int) : Int = a-b-c-d-e

//tham số biến đổi
fun substract(vararg nums:Int) : Int{
    var sum = 0
    for (item in nums){
        sum+=item
    }
    return sum
}
fun main() {
    var result = substract(1)
    result = substract(1,2,4)

    println(result)
    println(result)

    //- kotlin ko cho truyền đối số là 1 danh sách hay  1 mảng vào tham số biến đổi(vararg)
    val mylist = listOf(1,2,3,4,5,6)
    result = substract(*mylist.toIntArray())
    println(result)
}