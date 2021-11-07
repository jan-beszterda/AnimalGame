package group6.animalgame.utilities;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;

import java.util.Optional;
import java.util.Scanner;

/**
 * Dialog class is responsible for processing user input into console.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Dialogs {

    private static Scanner scanner = new Scanner(System.in);

    public static int showNumberOfPlayersDialog() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setHeaderText("Input number of players that will play the game!");
        dialog.initStyle(StageStyle.UNDECORATED);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        col1.setHalignment(HPos.RIGHT);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        col2.setHalignment(HPos.LEFT);
        grid.getColumnConstraints().addAll(col1, col2);

        Label label = new Label("Number of players:");
        label.setFont(new Font("Arial", 20));
        Spinner<Integer> integerSpinner = new Spinner<>(2, 4, 2, 1);
        ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        grid.add(label, 0, 0);
        grid.add(integerSpinner, 1, 0);

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(okButtonType);
        dialogPane.setMinSize(400, 100);
        dialogPane.setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return integerSpinner.getValue();
            }
            return null;
        });

        Optional<Integer> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return showNumberOfPlayersDialog();
        }
    }

    public static String showInputTextDialog(String headerText, String labelText) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setHeaderText(headerText);
        dialog.initStyle(StageStyle.UNDECORATED);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        col1.setHalignment(HPos.RIGHT);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        col2.setHalignment(HPos.LEFT);
        grid.getColumnConstraints().addAll(col1, col2);

        Label label = new Label(labelText);
        label.setFont(new Font("Arial", 20));
        TextField textField = new TextField();
        ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        grid.add(label, 0, 0);
        grid.add(textField, 1, 0);

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(okButtonType);
        dialogPane.setMinSize(400, 100);
        dialogPane.setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return textField.getText();
            }
            return null;
        });
        Platform.runLater(()->textField.requestFocus());
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return showInputTextDialog(headerText, labelText);
        }
    }

    public static void showAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    public static int showChooseGenderDialog(String animalType) {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setHeaderText("Choose gender for this " + animalType + "!");
        dialog.initStyle(StageStyle.UNDECORATED);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(50);
        row1.setValignment(VPos.CENTER);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(50);
        row2.setValignment(VPos.CENTER);
        grid.getRowConstraints().addAll(row1, row2);

        ToggleGroup gender = new ToggleGroup();

        RadioButton male = new RadioButton("Male");
        male.setFont(new Font("Arial", 18));
        male.setToggleGroup(gender);
        male.setUserData(0);
        RadioButton female = new RadioButton("Female");
        female.setFont(new Font("Arial", 18));
        female.setToggleGroup(gender);
        female.setUserData(1);
        ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        grid.add(male, 0, 0);
        grid.add(female, 0, 1);

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(okButtonType);
        dialogPane.setMinSize(400, 100);
        dialogPane.setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                if (gender.getSelectedToggle() != null) {
                    return (Integer) gender.getSelectedToggle().getUserData();
                }
            }
            return null;
        });

        Optional<Integer> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return showChooseGenderDialog(animalType);
        }
    }


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
