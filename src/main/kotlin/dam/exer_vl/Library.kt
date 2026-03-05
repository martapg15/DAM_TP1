package org.example.dam.exer_vl

class Library(var name: String) {
    var books: MutableList<Book> = mutableListOf()

    fun addBook(book: Book) {
        books.add(book)
        totalBooksAdded++
        println("Book '${book.title}' by ${book.author} has been added to the library.")
    }

    fun borrowBook(title: String) {
        val book = books.find { it.title.equals(title, true) }

        if (book == null) {
            println("Book not found")
            return
        }

        if (book.availableCopies > 0) {
            book.availableCopies--
            println("Successfully borrowed '${book.title}'. Copies remaining: ${book.availableCopies}")
        } else {
            println("Sorry, no copies of '${book.title}' are available to borrow.")
        }
    }

    fun returnBook(title: String) {
        val book = books.find { it.title.equals(title, true) }

        if (book == null) {
            println("Book not found")
            return
        }

        book.availableCopies++
        println("Book '${book.title}' returned successfully. Copies available: ${book.availableCopies}")
    }

    fun showBooks() {
        println(this)
    }

    fun searchByAuthor(author: String) {
        val str = StringBuilder().append("Books by $author:\n")
        books.forEach {
            if (it.author == author) {
                str.append(" - ${it.title} (${it.publicationYear}, ${it.availableCopies} ${if (it.availableCopies > 1) 
                    "copies" else "copy"} available)")
            }
        }
        println(str.toString())
    }

    override fun toString(): String {
        return buildString {
            appendLine("\n--- Library Catalog ---")
            for (book in books) {
                appendLine(book.toString())
            }
        }
    }

    companion object {
        var totalBooksAdded: Int = 0

        fun getTotalBooksCreated(): Int {
            return totalBooksAdded
        }
    }
}