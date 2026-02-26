package org.example.dam.exer_3

fun main() {
    val initialHeight = 100.0

    // example: 100 -> 100 * 0.6 = 60; 60 -> 60 * 0.6 = 36...
    val sequence = generateSequence(initialHeight) {
        previousHeight -> previousHeight * 0.6
    }

    val result = sequence
        .drop(1) // Drop the ball at the initial 100 meters to get the next bouncing heights
        .takeWhile { it >= 1.0 } // Will take any bounce that reaches at least 1 meter high
        .take(15) // Take the intended 15 qualifying bounces, if reached
        .toList() // Convert them into a list

    println("Bouncing heights: ${result.map { "%.2f".format(it) }}")
}