package org.example.dam.exer_vl

class PhysicalBook(
    title: String,
    author: String,
    publicationYear: String,
    availableCopies: Int,
    var weight: Int,
    var hasHardCover: Boolean = true
): Book(title, author, publicationYear, availableCopies)