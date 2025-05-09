package app;

import java.math.BigDecimal;

import static app.AppContext.scanner;

public class InputManager {

    public static String readStringFromUser(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextLine().trim();
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static int readIntFromUser(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static double readDoubleFromUser(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static BigDecimal readBigDecimalFromUser(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return new BigDecimal(scanner.next());
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static void enterToContinue() {
        readStringFromUser("Press enter to continue ");
        cleanUp();
    }

    public static void cleanUp(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
