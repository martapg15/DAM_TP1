package org.example.dam.exer_vl

import java.util.UUID

data class LibraryMember(
    var name: String,
    var membershipId: String = UUID.randomUUID().toString(),
    var borrowedBooks: MutableList<String> = mutableListOf()
)
