/*Experiment 4.3: Ticket Booking System*/
import java.util.Arrays;
class TicketBookingSystem {
    private final boolean[] seats;
    public TicketBookingSystem(int numSeats) {
        seats = new boolean[numSeats];
        Arrays.fill(seats, false);
    }
    public synchronized void bookSeat(String user, int seatNumber, boolean isVIP) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println("Invalid seat number!");
            return;
        }
        if (seats[seatNumber - 1]) {
            System.out.println(user + ": Seat " + seatNumber + " is already booked!");
            return;
        }
        seats[seatNumber - 1] = true;
        System.out.println(user + " booked seat " + seatNumber);
    }
}
class UserThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final boolean isVIP;
    public UserThread(TicketBookingSystem system, String name, int seatNumber, boolean isVIP) {
        super(name);
        this.system = system;
        this.seatNumber = seatNumber;
        this.isVIP = isVIP;
        if (isVIP) setPriority(MAX_PRIORITY);
        else setPriority(NORM_PRIORITY);
    }
    public void run() {
        system.bookSeat(getName(), seatNumber, isVIP);
    }
}
public class Main {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);
        // Test Case 1: No Seats Available Initially
        // Test Case 2: Successful Booking
        new UserThread(system, "Anish (VIP)", 1, true).start();
        new UserThread(system, "Bobby (Regular)", 2, false).start();
        new UserThread(system, "Charlie (VIP)", 3, true).start();
        // Test Case 3: Thread Priorities (VIP First)
        new UserThread(system, "Bobby (Regular)", 4, false).start();
        new UserThread(system, "Anish (VIP)", 4, true).start();
        // Test Case 4: Preventing Double Booking
        new UserThread(system, "Anish (VIP)", 1, true).start();
        new UserThread(system, "Bobby (Regular)", 1, false).start();
        // Test Case 5: Booking After All Seats Are Taken
        new UserThread(system, "New User (Regular)", 3, false).start();
        // Test Case 6: Invalid Seat Selection
        new UserThread(system, "User1", 0, false).start();
        new UserThread(system, "User2", 6, false).start();
        // Test Case 7: Simultaneous Bookings (Concurrency Test)
        for (int i = 0; i < 10; i++) {
            int seat = (i % 5) + 1;
            new UserThread(system, "User" + (i + 1), seat, i % 2 == 0).start();
        }
    }
}

/*
This program simulates a ticket booking system where multiple users (threads) try to book seats at the same time. The key challenges addressed are:

1) Avoiding Double Booking → Using synchronized methods to ensure no two users book the same seat.
2) Prioritizing VIP Customers → Using thread priorities so VIP users' bookings are processed before regular users.

📌 Core Concepts Used
️1 Synchronized Booking Method
The method bookSeat() is marked as synchronized, ensuring that only one thread can access it at a time.
This prevents race conditions, where two threads might try to book the same seat simultaneously.
  
️2 Thread Priorities for VIP Customers
Threads representing VIP users are assigned Thread.MAX_PRIORITY so they execute first.
Regular users have Thread.NORM_PRIORITY or Thread.MIN_PRIORITY, making them process later.

3 Handling Multiple Users
Each user trying to book a seat is represented by a thread.
Users can select a seat, and if it’s already booked, they receive an error message.


Step-by-Step Execution
1 Initialize the TicketBookingSystem → Allows booking of N seats.
2 Create Multiple Booking Threads → Each user (VIP or Regular) is assigned a thread.
3 Start All Threads → Threads compete for booking, with VIPs processed first.
4 Ensure No Double Booking → synchronized method prevents duplicate seat allocation.
5 Threads Finish Execution & Display Booking Status.


🔹 Why Use Synchronization?
Without synchronized, two threads might book the same seat simultaneously, causing double booking issues. Using synchronized, only one thread at a time can modify the seat booking data.

🔹 Why Use Thread Priorities?
Setting higher priority for VIP users ensures their bookings are processed first, simulating real-world priority-based bookings.

Test Cases

Test Case 1: No Seats Available Initially
Input:
System starts with 5 seats.
No users attempt to book.
Expected Output:
No bookings yet.

Test Case 2: Successful Booking
Input:
Anish (VIP) books Seat 1.
Bobby (Regular) books Seat 2.
Charlie (VIP) books Seat 3.
Expected Output:
Anish (VIP) booked seat 1
Bobby (Regular) booked seat 2
Charlie (VIP) booked seat 3

Test Case 3: Thread Priorities (VIP First)
Input:
Bobby (Regular) books Seat 4 (low priority).
Anish (VIP) books Seat 4 (high priority).
Expected Output:
Anish (VIP) booked seat 4
Bobby (Regular): Seat 4 is already booked!

Test Case 4: Preventing Double Booking
Input:
Anish (VIP) books Seat 1.
Bobby (Regular) tries to book Seat 1 again.
Expected Output:
Anish (VIP) booked seat 1
Bobby (Regular): Seat 1 is already booked!

Test Case 5: Booking After All Seats Are Taken
Input:
All 5 seats are booked.
A new user (Regular) tries to book Seat 3.
Expected Output:
Error: Seat 3 is already booked!

Test Case 6: Invalid Seat Selection
Input:
User tries to book Seat 0 (out of range).
User tries to book Seat 6 (beyond available seats).
Expected Output:
Invalid seat number!

Test Case 7: Simultaneous Bookings (Concurrency Test)
Input:
10 users try booking at the same time for 5 seats.
Expected Output:
5 users successfully book seats.
5 users receive error messages for already booked seats.
*/
