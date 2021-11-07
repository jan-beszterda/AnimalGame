package group6.animalgame.controllers;

import group6.animalgame.Main;
import group6.animalgame.animals.Animal;
import group6.animalgame.fodder.Food;
import group6.animalgame.logic.Game;
import javafx.collections.ObservableList;
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
    TextField feedAnimalResult;
    @FXML
    Button confirmButton;
    @FXML
    Button endButton;
    @FXML
    Label fodderAmountLabel;

    @FXML
    private void onConfirmButtonClick() {
        Animal a = animal.getSelectionModel().getSelectedItem();
        Food f = fodder.getSelectionModel().getSelectedItem();
        int amountToFeed = amount.getValue();
        int pHealth = a.getHealth();
        boolean success = game.getCurrentPlayer().feedAnAnimal(a, f, amountToFeed);
        if (success) {
            feedAnimalResult.setText(a.getName() + " is eating. Health increased by " + (a.getHealth()-pHealth) + "%." +
                    " Current health " + a.getHealth() + "%.");
        } else {
            feedAnimalResult.setText(a.getName() + " didn't like this. Health not increased.");
        }
    }

    @FXML
    private void onEndButtonClick() throws IOException {

        main.setScene("gameScene");
    }

    public void initializeValues(Main main) {
        this.main = main;
        if (game == null) {
            this.game = (Game) confirmButton.getScene().getWindow().getUserData();
        }
        animal.getItems().addAll(game.getCurrentPlayer().getAnimalsOwned());
        fodder.getItems().addAll(game.getCurrentPlayer().getFodderOwned().keySet());
        fodder.setOnAction(actionEvent -> {
            Food f = fodder.getSelectionModel().getSelectedItem();
            int max = game.getCurrentPlayer().getFodderOwned().get(f);
            SpinnerValueFactory.IntegerSpinnerValueFactory intFactory =
                    (SpinnerValueFactory.IntegerSpinnerValueFactory) amount.getValueFactory();
            intFactory.setMax(max);
            fodderAmountLabel.setText("(max: " + max + ")");
        });

    }
}
