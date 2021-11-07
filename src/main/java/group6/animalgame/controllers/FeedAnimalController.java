package group6.animalgame.controllers;

import group6.animalgame.Main;
import group6.animalgame.animals.Animal;
import group6.animalgame.fodder.Food;
import group6.animalgame.logic.Game;
import group6.animalgame.utilities.Dialogs;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class FeedAnimalController {

    Main main;
    Game game;

    @FXML
    ChoiceBox<Animal> animal;
    @FXML
    ChoiceBox<Food> fodder;
    @FXML
    Spinner<Integer> amount;
    @FXML
    Button confirmButton;
    @FXML
    Label fodderAmountLabel;

    @FXML
    private void onConfirmButtonClick() throws IOException {
        Animal a = animal.getSelectionModel().getSelectedItem();
        Food f = fodder.getSelectionModel().getSelectedItem();
        int amountToFeed = amount.getValue();
        int pHealth = a.getHealth();
        boolean success = game.getCurrentPlayer().feedAnAnimal(a, f, amountToFeed);
        if (success) {
            String info = a.getName() + " is eating. Health increased by " + (a.getHealth()-pHealth) + "%." +
                    " Current health " + a.getHealth() + "%.";
            Dialogs.showAlert("Success!", info);
        } else {
            String info = a.getName() + " didn't like this. Health not increased.";
            Dialogs.showAlert("Bad news!", info);
        }
        animal.getItems().clear();
        fodder.getItems().clear();
        initialiseChoiceBoxes();
        fodderAmountLabel.setText("");
        confirmButton.setDisable(true);
        game.moveTurn();
        //main.setScene("gameScene");
    }

    public void initializeValues(Main main) {
        this.main = main;
        if (game == null) {
            this.game = (Game) confirmButton.getScene().getWindow().getUserData();
        }
        initialiseChoiceBoxes();
        confirmButton.setDisable(true);
        fodder.setOnAction(actionEvent -> {
            Food f = fodder.getSelectionModel().getSelectedItem();
            if (f != null) {
                int max = game.getCurrentPlayer().getFodderOwned().get(f);
                SpinnerValueFactory.IntegerSpinnerValueFactory intFactory =
                        (SpinnerValueFactory.IntegerSpinnerValueFactory) amount.getValueFactory();
                intFactory.setMax(max);
                fodderAmountLabel.setText("(max: " + max + ")");
            }
            checkButtonDisabled();
        });
        animal.setOnAction(actionEvent -> {
            checkButtonDisabled();
        });
    }

    private void checkButtonDisabled() {
        if (animal.getSelectionModel().getSelectedItem() != null && fodder.getSelectionModel().getSelectedItem() != null) {
            confirmButton.setDisable(false);
        }
    }
    private void initialiseChoiceBoxes() {
        animal.getItems().addAll(game.getCurrentPlayer().getAnimalsOwned());
        animal.getSelectionModel().clearSelection();
        fodder.getItems().addAll(game.getCurrentPlayer().getFodderOwned().keySet());
        fodder.getSelectionModel().clearSelection();
    }
}
