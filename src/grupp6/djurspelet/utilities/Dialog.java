package grupp6.djurspelet.utilities;

import java.util.Scanner;

public class Dialog {

    private static Scanner scanner = new Scanner(System.in);

    public static int showDialog(String text, String... options) {
        clear();
        System.out.println("-".repeat(20));
        System.out.println(text);
        for (int i = 0; i < options.length; i++) {
            System.out.println(i+1 + ". " + options[i]);
        }
        return readIntInput(1, options.length) + 1;
    }

    public static int readIntInput(int min, int max) {
        System.out.println("-".repeat(20));
        if (max <= 0) {
            max = Integer.MAX_VALUE;
        }
        System.out.print("Your answer: ");
        int answer = -1;
        try {
            answer = Integer.parseInt(scanner.nextLine());
            if ( answer < min || answer > max) {
                System.out.println("Wrong input");
                answer = readIntInput(min, max);
            }
        } catch (Exception ignore) {
            System.out.println("Wrong input");
            answer = readIntInput(min, max);
        }
        return answer;
    }

    public static String readStringInput(String prompt) {
        String answer = "";
        while (answer.isEmpty()) {
            System.out.println(prompt + ": ");
            answer = scanner.nextLine();
        }
        return answer;
    }

    private static void clear() {
        System.out.println("\n".repeat(50));
    }
}
