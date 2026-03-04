package org.example.dam.exer_vl

class DigitalBook(
    title: String,
    author: String,
    publicationYear: String,
    availableCopies: Int,
    var fileSize: Double,
    var format: String
): Book(title, author, publicationYear, availableCopies)