package group6.animalgame.controllers;

import group6.animalgame.Main;
import group6.animalgame.animals.Animal;
import group6.animalgame.logic.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Arrays;

public class SellAnimalsController {

    Main main;
    Game game;

    @FXML
    ChoiceBox<Animal> animal;
    @FXML
    TextField animalSellResult;
    @FXML
    Button sellButton;
    @FXML
    Button endButton;

    @FXML
    private void onSellButtonClick() throws InterruptedException {
        Animal a = animal.getSelectionModel().getSelectedItem();
        int pay = game.getCurrentPlayer().sellAnimal(a, game.getStore());
        if (pay != -1) {
            animalSellResult.setText("You sold your " + a.getClass().getSimpleName() + " (" + a.getName() + "). You " +
                    "earned " + pay + ". Your current money is " + game.getCurrentPlayer().getMoney() + ".");
            animalSellResult.setDisable(true);
            animal.getItems().clear();
            initialiseAnimalChoice();
            sellButton.setDisable(true);
        } else {
            animalSellResult.setText("You cannot sell this!");
            animalSellResult.setDisable(true);
        }
    }

    @FXML
    private void onEndButtonClick() throws IOException {
        main.setScene("gameScene");
        game.moveTurn();
    }

    public void initializeValues(Main main) {
        this.main = main;
        this.game = (Game) sellButton.getScene().getWindow().getUserData();
        initialiseAnimalChoice();
        sellButton.setDisable(true);
        animal.setOnAction(actionEvent -> {
            checkButtonDisabled();
        });
    }

    private void checkButtonDisabled() {
        if (animal.getSelectionModel().getSelectedItem() != null) {
            sellButton.setDisable(false);
        }
    }
    private void initialiseAnimalChoice() {
        animal.getItems().addAll(game.getCurrentPlayer().getAnimalsOwned());
        animal.getSelectionModel().clearSelection();
    }
}
