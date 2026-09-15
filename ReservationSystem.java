import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReservationSystem {
    private final Map<String, User> users = new LinkedHashMap<>();
    private final List<Train> trains = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();
    private int pnrCounter = 10001;

    public ReservationSystem() {
        seedTrains();
        users.put("admin", new User("admin", "admin123", "Demo User"));
    }

    private void seedTrains() {
        trains.add(new Train(12123, "Deccan Express", "Pune", "Mumbai", 50, 220));
        trains.add(new Train(12124, "Intercity Express", "Pune", "Mumbai", 50, 300));
        trains.add(new Train(11029, "Koyna Express", "Pune", "Kolhapur", 50, 250));
        trains.add(new Train(12115, "Siddheshwar Express", "Solapur", "Mumbai", 50, 420));
    }

    public void register(Scanner scanner) {
        System.out.println("\n----------- REGISTRATION -----------");
        System.out.print("Full name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.isBlank() || password.isBlank() || name.isBlank()) {
            System.out.println("All fields are required.");
            return;
        }

        users.put(username, new User(username, password, name));
        System.out.println("Registration successful. Please login.");
    }

    public User login(Scanner scanner) {
        System.out.println("\n--------------- LOGIN ---------------");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = users.get(username);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + user.getFullName() + "!");
            return user;
        }

        System.out.println("Invalid username or password.");
        return null;
    }

    public void userMenu(Scanner scanner, User user) {
        while (true) {
            System.out.println("\n=========== MAIN MENU ===========");
            System.out.println("1. Search Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Booking");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Booking History");
            System.out.println("6. Logout");

            int choice = InputUtil.readInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1 -> searchTrains(scanner);
                case 2 -> bookTicket(scanner, user);
                case 3 -> viewBooking(scanner, user);
                case 4 -> cancelTicket(scanner, user);
                case 5 -> bookingHistory(user);
                case 6 -> {
                    System.out.println("Logged out successfully.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void searchTrains(Scanner scanner) {
        System.out.println("\n----------- SEARCH TRAINS -----------");
        System.out.print("Source: ");
        String source = scanner.nextLine().trim();

        System.out.print("Destination: ");
        String destination = scanner.nextLine().trim();

        boolean found = false;

        for (Train train : trains) {
            if (train.getSource().equalsIgnoreCase(source)
                    && train.getDestination().equalsIgnoreCase(destination)) {
                System.out.println(train);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No trains found for this route.");
        }
    }

    private void bookTicket(Scanner scanner, User user) {
        System.out.println("\n----------- BOOK TICKET -----------");
        displayAllAvailableTrains();

        int trainNumber = InputUtil.readInt(scanner, "Enter train number: ");
        Train train = findTrain(trainNumber);

        if (train == null) {
            System.out.println("Invalid train number.");
            return;
        }

        if (train.getAvailableSeats() <= 0) {
            System.out.println("No seats available.");
            return;
        }

        System.out.print("Passenger name: ");
        String name = scanner.nextLine().trim();
        int age = InputUtil.readAge(scanner);

        System.out.print("Gender: ");
        String gender = scanner.nextLine().trim();

        System.out.print("Seat preference (Window/Aisle/Any): ");
        String seat = scanner.nextLine().trim();

        Passenger passenger = new Passenger(name, age, gender, seat);

        if (train.reserveSeat()) {
            String pnr = "PNR" + pnrCounter++;
            Booking booking = new Booking(pnr, user.getUsername(), train, passenger);
            bookings.add(booking);

            System.out.println("\nTicket booked successfully!");
            booking.display();
        }
    }

    private void displayAllAvailableTrains() {
        System.out.println("\nAvailable Trains:");
        for (Train train : trains) {
            System.out.println(train);
        }
    }

    private Train findTrain(int trainNumber) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                return train;
            }
        }
        return null;
    }

    private Booking findUserBooking(String pnr, User user) {
        for (Booking booking : bookings) {
            if (booking.getPnr().equalsIgnoreCase(pnr)
                    && booking.getUsername().equals(user.getUsername())) {
                return booking;
            }
        }
        return null;
    }

    private void viewBooking(Scanner scanner, User user) {
        System.out.print("Enter PNR: ");
        String pnr = scanner.nextLine().trim();

        Booking booking = findUserBooking(pnr, user);

        if (booking == null) {
            System.out.println("Booking not found.");
        } else {
            booking.display();
        }
    }

    private void cancelTicket(Scanner scanner, User user) {
        System.out.print("Enter PNR to cancel: ");
        String pnr = scanner.nextLine().trim();

        Booking booking = findUserBooking(pnr, user);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        if (booking.getStatus().equals("CANCELLED")) {
            System.out.println("This booking is already cancelled.");
            return;
        }

        booking.cancel();
        booking.getTrain().releaseSeat();
        System.out.println("Ticket cancelled successfully.");
    }

    private void bookingHistory(User user) {
        System.out.println("\n----------- BOOKING HISTORY -----------");
        boolean found = false;

        for (Booking booking : bookings) {
            if (booking.getUsername().equals(user.getUsername())) {
                System.out.println(
                        booking.getPnr() + " | " +
                        booking.getPassenger().getName() + " | " +
                        booking.getTrain().getTrainName() + " | " +
                        booking.getStatus()
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("No bookings found.");
        }
    }
}