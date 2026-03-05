package org.example.dam.exer_vl

class PhysicalBook(
    title: String,
    author: String,
    publicationYear: Int,
    availableCopies: Int,
    var weight: Int,
    var hasHardcover: Boolean = true
): Book(title, author, publicationYear, availableCopies) {
    override fun getStorageInfo(): String {
        return "Physical book: $weight g, Hardcover: ${if (hasHardcover) "Yes" else "No"}"
    }

    override fun toString(): String {
        return super.toString() + "\n Storage info: ${getStorageInfo()}"
    }
}