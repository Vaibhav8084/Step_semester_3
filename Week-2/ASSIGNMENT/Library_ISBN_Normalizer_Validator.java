package ASSIGNMENT;
import java.util.Scanner;

public class Library_ISBN_Normalizer_Validator {

    static String normalizeCode(String raw) {

        raw = raw.trim();

        if (raw.length() < 3) {
            return raw;
        }

        String publisherCode = raw.substring(0, 3).toUpperCase();
        String remainingCode = raw.substring(3);

        return publisherCode + remainingCode;
    }

    static String validateAndFormat(String code) {

        // Check total length
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        // Check first 3 characters are letters
        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Check remaining 10 characters are digits
        for (int i = 3; i < 13; i++) {

            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        // Format the valid code
        StringBuilder result = new StringBuilder();

        result.append("[");
        result.append(code.substring(0, 3));
        result.append("] YEAR: ");
        result.append(code.substring(3, 7));
        result.append(" | CATALOG: ");
        result.append(code.substring(7, 13));

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ISBN code: ");
        String rawCode = sc.nextLine();

        String normalizedCode = normalizeCode(rawCode);

        String result = validateAndFormat(normalizedCode);

        System.out.println(result);

        sc.close();
    }
}