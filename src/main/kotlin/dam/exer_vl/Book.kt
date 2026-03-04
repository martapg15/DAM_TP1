package org.example.dam.exer_vl

abstract class Book(
    var title: String,
    var author: String,
    publicationYear: String,
    availableCopies: Int
) {
    var publicationYear: String = publicationYear
        get() {
            return when {
                field.toInt() < 1980 -> "Classic"
                field.toInt() in 1980..2010 -> "Modern"
                else -> "Contemporary"
            }
        }

    var availableCopies: Int = availableCopies
        set(value) {
            if (value < 0) throw IllegalArgumentException("Available Copies cannot be negative")
            if (value == 0) println("Warning: Book is now out of stock!")
            field = value
        }

    init {
        println("The book: '$title' by $author has been created.")
    }
}