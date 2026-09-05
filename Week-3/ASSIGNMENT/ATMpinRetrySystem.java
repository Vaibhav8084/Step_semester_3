package ASSIGNMENT;
import java.util.Scanner;

public class ATMpinRetrySystem {

    public static void atmPinRetry(String correctPin, String[] attempts) {

        int attempt = 0;
        boolean success = false;

        while (attempt < attempts.length && attempt < 3 && !success) {

            if (attempts[attempt].equals(correctPin)) {
                success = true;
                System.out.println("PIN accepted");
                break;
            }

            attempt++;
        }

        if (!success) {
            System.out.println("Card blocked — too many incorrect attempts");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String correctPin = sc.next();

        String[] attempts = new String[3];

        for (int i = 0; i < 3; i++) {
            attempts[i] = sc.next();
        }

        atmPinRetry(correctPin, attempts);

        sc.close();
    }
}