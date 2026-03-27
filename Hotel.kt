import java.util.*

data class Room(val roomNumber: Int, var isBooked: Boolean = false, var guestName: String = "")

class Hotel {
    private val rooms = mutableListOf<Room>()

    fun addRoom(roomNumber: Int) {
        if (rooms.any { it.roomNumber == roomNumber }) {
            println("Room already exists!")
            return
        }
        rooms.add(Room(roomNumber))
        println("Room added successfully!")
    }

    fun viewRooms() {
        if (rooms.isEmpty()) {
            println("No rooms available.")
            return
        }
        for (room in rooms) {
            println("Room: ${room.roomNumber}, Booked: ${room.isBooked}, Guest: ${room.guestName}")
        }
    }

    fun bookRoom(roomNumber: Int, guestName: String) {
        val room = rooms.find { it.roomNumber == roomNumber }
        when {
            room == null -> println("Room not found.")
            room.isBooked -> println("Room already booked.")
            else -> {
                room.isBooked = true
                room.guestName = guestName
                println("Room booked successfully!")
            }
        }
    }

    fun checkoutRoom(roomNumber: Int) {
        val room = rooms.find { it.roomNumber == roomNumber }
        when {
            room == null -> println("Room not found.")
            !room.isBooked -> println("Room is not booked.")
            else -> {
                room.isBooked = false
                room.guestName = ""
                println("Checkout successful!")
            }
        }
    }
}

fun safeIntInput(scanner: Scanner): Int {
    while (true) {
        try {
            return scanner.nextInt()
        } catch (e: InputMismatchException) {
            println("Enter valid number!")
            scanner.next()
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val hotel = Hotel()

    while (true) {
        println("\n--- Hotel Management ---")
        println("1. Add Room")
        println("2. View Rooms")
        println("3. Book Room")
        println("4. Checkout Room")
        println("5. Exit")

        print("Enter choice: ")
        val choice = safeIntInput(scanner)

        when (choice) {
            1 -> {
                print("Enter Room Number: ")
                val roomNumber = safeIntInput(scanner)
                hotel.addRoom(roomNumber)
            }
            2 -> hotel.viewRooms()
            3 -> {
                print("Enter Room Number: ")
                val roomNumber = safeIntInput(scanner)
                print("Enter Guest Name: ")
                scanner.nextLine()
                val name = scanner.nextLine()
                hotel.bookRoom(roomNumber, name)
            }
            4 -> {
                print("Enter Room Number: ")
                val roomNumber = safeIntInput(scanner)
                hotel.checkoutRoom(roomNumber)
            }
            5 -> {
                println("Thank you!")
                break
            }
            else -> println("Invalid choice!")
        }
    }
}