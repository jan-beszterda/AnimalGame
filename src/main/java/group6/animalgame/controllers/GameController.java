package group6.animalgame.controllers;

import group6.animalgame.Main;
import group6.animalgame.logic.Game;
import group6.animalgame.utilities.FileUtilities;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class GameController {

    private Main main;
    private Game game;
    private FileChooser fileChooser = new FileChooser();

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
        File selectedFile = fileChooser.showSaveDialog(quitGameButton.getScene().getWindow());
        if (selectedFile != null) {
            FileUtilities.saveGameToFile(selectedFile, game);
            Platform.exit();
        }
    }

    public void initializeValues(Main main) {
        this.main = main;
        if (game == null) {
            this.game = (Game) buyAnimalsButton.getScene().getWindow().getUserData();
        }
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Save files", "*.ser"));
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
