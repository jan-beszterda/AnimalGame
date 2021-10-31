package grupp6.djurspelet.utilities;

import java.util.Scanner;

/**
 * Dialog class is responsible for processing user input into console.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Dialog {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Static method to display a question with options to chose from.
     * @param text question to ask the player
     * @param options options the player can choose from
     * @return option number chosen by the player
     */
    public static int showDialog(String text, String... options) {
        System.out.println("-".repeat(20));
        System.out.println(text);
        for (int i = 0; i < options.length; i++) {
            System.out.println(i+1 + ". " + options[i]);
        }
        return readIntInput(1, options.length);
    }

    /**
     * Method responsible for reading numeric input from the user.
     * @param min minimum number allowed
     * @param max maximum number allowed
     * @return number chosen by the player
     */
    public static int readIntInput(int min, int max) {
        System.out.println("-".repeat(20));
        if (max == 0) {
            max = Integer.MAX_VALUE;
        }
        System.out.print("Your answer: ");
        int answer;
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

    /**
     * Method responsible for reading text input from the player.
     * @param prompt question to ask the player
     * @return text answer from the player
     */
    public static String readStringInput(String prompt) {
        String answer = "";
        while (answer.isEmpty()) {
            System.out.println(prompt);
            System.out.print("Your answer: ");
            answer = scanner.nextLine();
        }
        return answer;
    }

    /**
     * Method responsible for adding some empty space in console output.
     */
    public static void clear() {
        System.out.println("\n".repeat(3));
    }
}
