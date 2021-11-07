package group6.animalgame.controllers;

import group6.animalgame.Main;
import group6.animalgame.logic.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class BuyAnimalsController {

    private Main main;
    private Game game;

    @FXML
    ToggleGroup animals;
    @FXML
    RadioButton horse;
    @FXML
    RadioButton cow;
    @FXML
    RadioButton dog;
    @FXML
    RadioButton cat;
    @FXML
    RadioButton pig;

    @FXML
    Button buyButton;
    @FXML
    Button endButton;

    @FXML
    TextField buyAnimalResult;

    @FXML
    private void onBuyButtonClick() {
        String animalType = (String) animals.getSelectedToggle().getUserData();
        boolean success = game.getCurrentPlayer().buyAnimal(animalType, game.getStore());
        if (success) {
            int price = game.getStore().getPrice(animalType);
            buyAnimalResult.setText("You bought a " + animalType + " and paid " + price + ". You have " +
                    game.getCurrentPlayer().getMoney() + " left.");
        } else {
            buyAnimalResult.setText("You cannot buy this!");
        }
        animals.selectToggle(horse);
    }

    @FXML
    private void onEndButtonClick() throws IOException {
        game.moveTurn();
        //main.setScene("gameScene");
    }

    public void initializeValues(Main main) {
        this.main = main;
        if (game == null) {
            this.game = (Game) buyButton.getScene().getWindow().getUserData();
        }
        horse.setUserData("Horse");
        cow.setUserData("Cow");
        dog.setUserData("Dog");
        cat.setUserData("Cat");
        pig.setUserData("Pig");
        animals.selectToggle(horse);
    }
}
