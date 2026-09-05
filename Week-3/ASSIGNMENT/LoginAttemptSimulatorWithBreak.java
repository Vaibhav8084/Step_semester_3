package ASSIGNMENT;
import java.util.Scanner;

public class LoginAttemptSimulatorWithBreak {

    public static void simulateLogin(String correctCode, String[] attempts) {

        boolean accessGranted = false;

        for (int i = 0; i < attempts.length && i < 3; i++) {

            if (attempts[i].equals(correctCode)) {
                System.out.println("Access granted on attempt " + (i + 1));
                accessGranted = true;
                break;
            }
        }

        if (!accessGranted) {
            System.out.println("Access denied — all attempts used");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String correctCode = sc.next();

        String[] attempts = new String[3];

        for (int i = 0; i < 3; i++) {
            attempts[i] = sc.next();
        }

        simulateLogin(correctCode, attempts);

        sc.close();
    }
}