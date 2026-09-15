import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem system = new ReservationSystem();

        while (true) {
            System.out.println("\n======================================");
            System.out.println("     ONLINE RESERVATION SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = InputUtil.readInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1 -> system.register(scanner);
                case 2 -> {
                    User user = system.login(scanner);
                    if (user != null) {
                        system.userMenu(scanner, user);
                    }
                }
                case 3 -> {
                    System.out.println("Thank you for using the system.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}