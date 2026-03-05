package org.example.dam.exer_vl

abstract class Book(
    var title: String,
    var author: String,
    publicationYear: Int,
    availableCopies: Int
) {
    // Minor change to the prior "publicationYear: String". publicationYear is now an Int as expected and its value
    // is saved internally
    private var _publicationYear: Int = publicationYear

    val publicationYear: String
        get() {
            return when {
                _publicationYear < 1980 -> "Classic"
                _publicationYear in 1980..2010 -> "Modern"
                else -> "Contemporary"
            }
        }

    var availableCopies: Int = availableCopies
        set(value) {
            if (value < 0) throw IllegalArgumentException("Available Copies cannot be negative")
            field = value
            if (value == 0) println("Warning: Book is now out of stock!")
        }

    init {
        println("The book: '$title' by $author has been created.")
    }

    abstract fun getStorageInfo(): String

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Title: $title, author: $author, publication year: $publicationYear, available copies: $availableCopies")
        return sb.toString()
    }
}