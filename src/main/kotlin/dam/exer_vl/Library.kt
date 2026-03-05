package org.example.dam.exer_vl

class Library {
    var books: MutableList<Book> = mutableListOf()

    fun addBook(book: Book) {
        books.add(book)
        totalBooksAdded++
    }

    fun borrowBook(title: String) {
        val book = books.find { it.title.equals(title, true) }

        if (book == null) {
            println("Book not found")
            return
        }

        if (book.availableCopies > 0) {
            book.availableCopies--
            println("You borrowed the book: '{$book.title}'. There's now {$book.availableCopies} copies left.}")
        } else {
            println("No available copies to borrow.")
        }
    }

    fun returnBook(title: String) {
        val book = books.find { it.title.equals(title, true) }

        if (book == null) {
            println("Book not found")
            return
        }

        if (book.availableCopies > 0) {
            book.availableCopies++
            println("You just return the book: '{$book.title}'. There's now {$book.availableCopies} copies left.}")
        }
    }

    fun showBooks() {
        println(this)
    }

    fun searchByAuthor(author: String) {
        val str = StringBuilder().append("Books by $author:\n")
        books.forEach {
            if (it.author == author) {
                println("Title: ${it.title} (${it.publicationYear}), Copies: ${it.availableCopies}")
            }
        }
        println(str.toString())
    }

    override fun toString(): String {
        return buildString {
            appendLine("--- Library Catalog ---")
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