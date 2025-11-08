import java.util.*;

class Bus {
    int busNo;
    String source;
    String destination;
    int totalSeats;
    int availableSeats;

    Bus(int busNo, String source, String destination, int totalSeats) {
        this.busNo = busNo;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    void displayBusInfo() {
        System.out.println("Bus No: " + busNo + " | From: " + source + " | To: " + destination + " | Available Seats: " + availableSeats);
    }

    boolean bookSeat(int count) {
        if (availableSeats >= count) {
            availableSeats -= count;
            return true;
        } else {
            return false;
        }
    }

    void cancelSeat(int count) {
        availableSeats += count;
        if (availableSeats > totalSeats) {
            availableSeats = totalSeats;
        }
    }
}

class Booking {
    String passengerName;
    int busNo;
    int seatCount;

    Booking(String passengerName, int busNo, int seatCount) {
        this.passengerName = passengerName;
        this.busNo = busNo;
        this.seatCount = seatCount;
    }

    void displayBooking() {
        System.out.println("Passenger: " + passengerName + " | Bus No: " + busNo + " | Seats: " + seatCount);
    }
}

public class BusReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Bus> buses = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        buses.add(new Bus(101, "Coimbatore", "Chennai", 40));
        buses.add(new Bus(102, "Coimbatore", "Madurai", 35));
        buses.add(new Bus(103, "Coimbatore", "Bangalore", 30));

        int choice;
        do {
            System.out.println("\n=== BUS RESERVATION SYSTEM ===");
            System.out.println("1. Show Available Buses");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Bookings");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> showBuses();
                case 2 -> bookTicket();
                case 3 -> viewBookings();
                case 4 -> cancelTicket();
                case 5 -> System.out.println("Thank you for using Bus Reservation System!");
                default -> System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);
    }

    static void showBuses() {
        for (Bus b : buses) {
            b.displayBusInfo();
        }
    }

    static void bookTicket() {
        System.out.print("Enter Bus No: ");
        int busNo = sc.nextInt();
        System.out.print("Enter number of seats: ");
        int seats = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter passenger name: ");
        String name = sc.nextLine();

        for (Bus b : buses) {
            if (b.busNo == busNo) {
                if (b.bookSeat(seats)) {
                    bookings.add(new Booking(name, busNo, seats));
                    System.out.println("✅ Booking successful!");
                } else {
                    System.out.println("❌ Not enough seats available.");
                }
                return;
            }
        }
        System.out.println("❌ Bus not found!");
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking bk : bookings) {
                bk.displayBooking();
            }
        }
    }

    static void cancelTicket() {
        System.out.print("Enter passenger name to cancel: ");
        sc.nextLine();
        String name = sc.nextLine();
        Booking toRemove = null;

        for (Booking bk : bookings) {
            if (bk.passengerName.equalsIgnoreCase(name)) {
                for (Bus b : buses) {
                    if (b.busNo == bk.busNo) {
                        b.cancelSeat(bk.seatCount);
                        break;
                    }
                }
                toRemove = bk;
                break;
            }
        }

        if (toRemove != null) {
            bookings.remove(toRemove);
            System.out.println("✅ Ticket cancelled successfully.");
        } else {
            System.out.println("❌ Booking not found!");
        }
    }
}
