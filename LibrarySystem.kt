import java.util.*

data class Book(val id: Int, val name: String, var isIssued: Boolean = false)

class Library {
    private val books = mutableListOf<Book>()

    fun addBook(id: Int, name: String) {
        books.add(Book(id, name))
        println("Book added successfully!")
    }

    fun viewBooks() {
        if (books.isEmpty()) {
            println("No books available.")
            return
        }
        for (book in books) {
            println("ID: ${book.id}, Name: ${book.name}, Issued: ${book.isIssued}")
        }
    }

    fun issueBook(id: Int) {
        val book = books.find { it.id == id }
        if (book != null && !book.isIssued) {
            book.isIssued = true
            println("Book issued successfully!")
        } else {
            println("Book not available.")
        }
    }

    fun returnBook(id: Int) {
        val book = books.find { it.id == id }
        if (book != null && book.isIssued) {
            book.isIssued = false
            println("Book returned successfully!")
        } else {
            println("Invalid return.")
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val library = Library()

    while (true) {
        println("\n--- Library Menu ---")
        println("1. Add Book")
        println("2. View Books")
        println("3. Issue Book")
        println("4. Return Book")
        println("5. Exit")

        print("Enter choice: ")
        val choice = scanner.nextInt()

        when (choice) {
            1 -> {
                print("Enter Book ID: ")
                val id = scanner.nextInt()
                print("Enter Book Name: ")
                scanner.nextLine()
                val name = scanner.nextLine()
                library.addBook(id, name)
            }
            2 -> library.viewBooks()
            3 -> {
                print("Enter Book ID to issue: ")
                val id = scanner.nextInt()
                library.issueBook(id)
            }
            4 -> {
                print("Enter Book ID to return: ")
                val id = scanner.nextInt()
                library.returnBook(id)
            }
            5 -> {
                println("Exiting...")
                break
            }
            else -> println("Invalid choice!")
        }
    }
}