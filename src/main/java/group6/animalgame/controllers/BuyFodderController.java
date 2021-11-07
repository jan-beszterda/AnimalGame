package group6.animalgame.controllers;

import group6.animalgame.Main;
import group6.animalgame.logic.Game;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class BuyFodderController {

    Main main;
    Game game;

    @FXML
    ToggleGroup fodder;
    @FXML
    RadioButton meat;
    @FXML
    RadioButton grass;
    @FXML
    RadioButton corn;
    @FXML
    Spinner<Integer> amount;
    @FXML
    TextField buyFodderResult;
    @FXML
    Button buyButton;
    @FXML
    Button endButton;

    @FXML
    private void onBuyButtonClick() {
        String fodderType = (String) fodder.getSelectedToggle().getUserData();
        int amountToBuy = 0;
        amountToBuy = amount.getValue();
        boolean success = game.getCurrentPlayer().buyFodder(fodderType, amountToBuy, game.getStore());
        if (success) {
            int price = game.getStore().getPrice(fodderType) * amountToBuy;
            buyFodderResult.setText("You bought " + amountToBuy + " kg of " + fodderType + " and paid " + price + ". You have " +
                    game.getCurrentPlayer().getMoney() + " left.");
        } else {
            buyFodderResult.setText("You cannot buy this!");
        }
    }

    @FXML
    private void onEndButtonClick() throws IOException {
        main.setScene("gameScene");
    }


    public void initializeValues(Main main) {
        this.main = main;
        if (game == null) {
            this.game = (Game) buyButton.getScene().getWindow().getUserData();
        }
        meat.setUserData("Meat");
        grass.setUserData("Grass");
        corn.setUserData("Corn");
        fodder.selectToggle(meat);
    }
}
