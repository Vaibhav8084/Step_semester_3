package ASSIGNMENT;
import java.util.Scanner;

public class TrafficSignalStreakAnalyzer {

    static void findLongestStreak(String signalLog) {

        if (signalLog.length() == 0) {
            System.out.println("Signal log is empty.");
            return;
        }

        char longestChar = signalLog.charAt(0);
        int longestCount = 1;

        char currentChar = signalLog.charAt(0);
        int currentCount = 1;

        for (int i = 1; i < signalLog.length(); i++) {

            if (signalLog.charAt(i) == currentChar) {
                currentCount++;
            } else {

                if (currentCount > longestCount) {
                    longestCount = currentCount;
                    longestChar = currentChar;
                }

                currentChar = signalLog.charAt(i);
                currentCount = 1;
            }
        }

        // Check the last streak
        if (currentCount > longestCount) {
            longestCount = currentCount;
            longestChar = currentChar;
        }

        System.out.println("Longest Streak: '" + longestChar
                + "' repeated " + longestCount + " times");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Signal Log: ");
        String signalLog = sc.nextLine().toUpperCase();

        findLongestStreak(signalLog);

        sc.close();
    }
}