package com.chungdoan.fundamentals.variables

fun main() {
    //val cannot change
    val name : String
    name = "Chungdt"
    println(name)

    //var can change
    //biên dịch theo dạng từ trên xuống
    var age = 10
    println("show data type : " + age.javaClass + " " + age)
    age = 99
    println(age)

    var title : String = "hi"
    println("day la $title")

    //Any
    var a : Any
    a = 9
    a = "hihi"
    a = 9.08
    a = true

    //gọi biến cần xác định 1 trong 2 thằng l
    //1 gán giá trị cho biến
    //2 khai báo type cho biến
    //var b <-
    //b = 3

}