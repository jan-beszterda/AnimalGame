package group6.animalgame.controllers;

import group6.animalgame.Main;
import group6.animalgame.logic.Game;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {

    Main main;
    Game game;
    FileChooser fileChooser = new FileChooser();

    @FXML
    Button buyAnimalsButton;
    @FXML
    Button buyFodderButton;
    @FXML
    Button feedAnimalButton;
    @FXML
    Button mateAnimalsButton;
    @FXML
    Button sellAnimalsButton;
    @FXML
    Button quitGameButton;

    @FXML
    TextArea textArea;

    @FXML
    protected void onBuyAnimalsButtonClick() throws IOException {
        buyAnimalsButton.getScene().getWindow().setUserData(game);
        main.setScene("buyAnimalsScene");
        System.out.println("buy animals button clicked!");
    }

    @FXML
    protected void onBuyFodderButtonClick() throws IOException {
        buyFodderButton.getScene().getWindow().setUserData(game);
        main.setScene("buyFodderScene");
        System.out.println("buy fodder button clicked!");
    }

    @FXML
    protected void onFeedAnimalButtonClick() throws IOException {
        feedAnimalButton.getScene().getWindow().setUserData(game);
        main.setScene("feedAnimalScene");
        System.out.println("feed animal button clicked!");
    }

    @FXML
    protected void onMateAnimalsButtonClick() throws IOException {
        mateAnimalsButton.getScene().getWindow().setUserData(game);
        main.setScene("mateAnimalsScene");
        System.out.println("mate animals button clicked!");
    }

    @FXML
    protected void onSellAnimalsButtonClick() throws IOException {
        sellAnimalsButton.getScene().getWindow().setUserData(game);
        main.setScene("sellAnimalsScene");
        System.out.println("sell animals button clicked!");
    }

    @FXML
    protected void onQuitGameButtonClick() {
        System.exit(0);
    }

    public void initializeValues(Main main) {
        this.main = main;
        if (game == null) {
            this.game = (Game) buyAnimalsButton.getScene().getWindow().getUserData();
        }
        textArea.setText(game.showPlayerStatus());
        checkDisableButtons();
    }

    private void checkDisableButtons() {
        if (!game.allowAnimalSale()) {
            sellAnimalsButton.setDisable(true);
        }
        if (!game.allowAnimalPurchase()) {
            buyAnimalsButton.setDisable(true);
        }
        if (!game.allowFodderPurchase()) {
            buyFodderButton.setDisable(true);
        }
        if (!game.allowMateAnimals()) {
            mateAnimalsButton.setDisable(true);
        }
        if (!game.allowFeedAnimal()) {
            feedAnimalButton.setDisable(true);
        }
    }
}
