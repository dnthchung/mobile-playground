package com.chungdoan.fundamentals.ranges

fun main() {
    val range = 1..10 // Tạo ra một range từ 1 đến 10

    val firstElement = range.first // Lấy phần tử đầu tiên trong range
    val lastElement = range.last // Lấy phần tử cuối cùng trong range

    println("First element: $firstElement")
    println("Last element: $lastElement")
    println("Step : ${range.step}")

    // In ra tất cả các số trong range với step là 2
    println("Numbers in the range with step 2:")
    for (num in range step 2) {
        println(num)
    }
    println("=======================")
    val range1 = 1..10 // Tạo ra một range từ 1 đến 10
    val range2 = 1..<5 // Tạo ra một range từ 1 đến 4 (bỏ qua 5)

    // Sử dụng step() để in ra tất cả các số trong range1 với bước là 2
    println("Numbers in the range1 with step 2:")
    for (num in range1.step(2)) {
        println(num)
    }

    // Sử dụng reverse() để in ra tất cả các số trong range2 theo thứ tự đảo ngược
    println("Numbers in the range2 in reverse order:")
    for (num in range2.reversed()) {
        println(num)
    }
}