package org.example.dam.exer_vl

class PhysicalBook(
    title: String,
    author: String,
    publicationYear: Int,
    availableCopies: Int,
    var weight: Int,
    var hasHardcover: Boolean = true
): Book(title, author, publicationYear, availableCopies) {

    // After looking at the assessment criteria, it made sense to test some more stuff, so I decided to add a
    // secondary constructor to this class in specific, because if we think on a real world scenario, it is possible
    // that a library only has one copy of a specific book.
    //
    // So with this constructor I am limiting the number of available copies to 1.
    constructor(title: String, author: String, publicationYear: Int, weight: Int, hasHardcover: Boolean = true) :
            this(title, author, publicationYear, 1, weight, hasHardcover)

    override fun getStorageInfo(): String {
        return "Physical book: $weight g, Hardcover: ${if (hasHardcover) "Yes" else "No"}"
    }

    override fun toString(): String {
        return super.toString() + "\n Storage info: ${getStorageInfo()}"
    }
}