package org.example.dam.exer_vl

fun main() {
    val library = Library("Central Library")

    val digitalBook = DigitalBook(
        "Kotlin in Action",
        "Dmitry Jemerov",
        2017,
        5,
        4.5,
        "PDF"
    )

    val physicalBook = PhysicalBook(
        "Clean Code",
        "Robert C. Martin",
        2008,
        3,
        650,
        true
    )

    val physicalBook2 = PhysicalBook(
        "If It Bleeds",
        "Stephen King",
        2020,
        500,
        false
    )

    val classicBook = PhysicalBook(
        "1984",
        "George Orwell",
        1949,
        2,
        400,
        false
    )

    library.addBook(digitalBook)
    library.addBook(physicalBook)
    library.addBook(physicalBook2)
    library.addBook(classicBook)

    library.showBooks()
    println("Total number of books: ${Library.getTotalBooksCreated()}")

    val student = library.registerMember("Marta Garcia")
    val student1 = library.registerMember("Joaquim Silva")

    println("\n--- Borrowing Books ---")
    library.borrowBook("Clean Code", student)
    library.borrowBook("1984", student)
    library.borrowBook("1984", student)
    library.borrowBook("1984", student) // Should fail - no copies left
    println("\n${student.name} (ID: ${student.membershipId}) has borrowed: ${student.borrowedBooks}\n")

    library.borrowBook("If It Bleeds", student1)
    library.borrowBook("If It Bleeds", student1) // Should fail - no copies left
    println("\n${student1.name} (ID: ${student1.membershipId}) has borrowed: ${student1.borrowedBooks}")

    println("\n--- Returning Books ---")
    library.returnBook("1984", student)
    library.returnBook("1984", student)
    println("\n${student.name} (ID: ${student.membershipId}) has borrowed: ${student.borrowedBooks}")

    println("\n--- Search by Author ---")
    library.searchByAuthor("George Orwell")
    library.searchByAuthor("Stephen King")

    println("\n--- Returning Books ---")
    library.returnBook("If It Bleeds", student1)
    println("\n${student1.name} (ID: ${student1.membershipId}) has borrowed: ${student1.borrowedBooks}")

    println("\n--- Search by Author ---")
    library.searchByAuthor("Stephen King")
}