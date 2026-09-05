package ASSIGNMENT;
import java.util.Scanner;

public class WarehouseInventoryBalancer {

    static void analyzeInventory(int[] sectionA, int[] sectionB) {

        int totalA = 0;
        int totalB = 0;

        int max = sectionA[0];
        String maxSection = "Section A";
        int maxIndex = 0;

        // Calculate total of Section A and find maximum
        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];

            if (sectionA[i] > max) {
                max = sectionA[i];
                maxSection = "Section A";
                maxIndex = i;
            }
        }

        // Calculate total of Section B and find maximum
        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];

            if (sectionB[i] > max) {
                max = sectionB[i];
                maxSection = "Section B";
                maxIndex = i;
            }
        }

        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);

        if (totalA == totalB)
            System.out.println("Status: Balanced");
        else
            System.out.println("Status: Not Balanced");

        System.out.println("Highest Quantity: " + max +
                " (" + maxSection + ", Item " + (maxIndex + 1) + ")");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] sectionA = new int[n];
        int[] sectionB = new int[n];

        System.out.println("Enter quantities for Section A:");
        for (int i = 0; i < n; i++) {
            sectionA[i] = sc.nextInt();
        }

        System.out.println("Enter quantities for Section B:");
        for (int i = 0; i < n; i++) {
            sectionB[i] = sc.nextInt();
        }

        analyzeInventory(sectionA, sectionB);

        sc.close();
    }
}