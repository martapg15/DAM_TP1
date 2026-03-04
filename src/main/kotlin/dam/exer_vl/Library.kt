package org.example.dam.exer_vl

class Library {
    var books: MutableList<Book> = mutableListOf()

    fun addBook(book: Book) {
        books.add(book)
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
        books.forEach { book ->
            println("Title: ${book.title}, Author: ${book.author}, Copies: ${book.availableCopies}")
        }
    }

    fun searchByAuthor(author: String) {
        val authorBook = books.filter { it.title.equals(author, true) }

        books.forEach { book ->
            println("Title: ${book.title}, Copies: ${book.availableCopies}")
        }
    }
}