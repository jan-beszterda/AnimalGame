package group6.animalgame.controllers;

import group6.animalgame.Main;
import group6.animalgame.animals.Animal;
import group6.animalgame.logic.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class MateAnimalsController {

    Main main;
    Game game;

    @FXML
    ChoiceBox<Animal> firstAnimal;
    @FXML
    ChoiceBox<Animal> secondAnimal;
    @FXML
    Button confirmButton;

    @FXML
    private void onConfirmButtonClick() {
        Animal a1 = firstAnimal.getSelectionModel().getSelectedItem();
        Animal a2 = secondAnimal.getSelectionModel().getSelectedItem();
        game.getCurrentPlayer().mateAnimals(a1, a2);
    }

    public void initializeValues(Main main) {
        this.main = main;
        if (game == null) {
            this.game = (Game) confirmButton.getScene().getWindow().getUserData();
        }
        firstAnimal.getItems().addAll(game.getCurrentPlayer().getAnimalsOwned());
        secondAnimal.getItems().addAll(game.getCurrentPlayer().getAnimalsOwned());
        confirmButton.setDisable(true);
        firstAnimal.setOnAction(actionEvent -> {
            checkButtonDisabled();
        });
        secondAnimal.setOnAction(actionEvent -> {
            checkButtonDisabled();
        });
    }

    private void checkButtonDisabled() {
        if (firstAnimal.getSelectionModel().getSelectedItem() != null && secondAnimal.getSelectionModel().getSelectedItem() != null) {
            confirmButton.setDisable(false);
        }
    }
}
