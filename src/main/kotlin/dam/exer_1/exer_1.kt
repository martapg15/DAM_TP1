package org.example.dam.exer_1

fun main() {
    // a)
    val intArr = IntArray(50) { (it + 1) * (it + 1) }
    println(intArr.contentToString())

    //b)
    val mapArr = (1..50).map { it * it }.toIntArray()
    println(mapArr.contentToString())

    //c)
    val arr = Array(50) { (it + 1) * (it + 1) }
    println(arr.contentToString())
}