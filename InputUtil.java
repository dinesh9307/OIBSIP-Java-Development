import java.util.Scanner;

public final class InputUtil {
    private InputUtil() {}

    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static int readAge(Scanner scanner) {
        while (true) {
            int age = readInt(scanner, "Age: ");
            if (age >= 1 && age <= 120) {
                return age;
            }
            System.out.println("Age must be between 1 and 120.");
        }
    }
}