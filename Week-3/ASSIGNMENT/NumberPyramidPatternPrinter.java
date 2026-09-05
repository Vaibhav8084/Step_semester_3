package ASSIGNMENT;
import java.util.Scanner;

public class NumberPyramidPatternPrinter {

    public static void printNumberPyramid(int n) {

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= i; j++) {
                System.out.print(i + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        printNumberPyramid(n);

        sc.close();
    }
}