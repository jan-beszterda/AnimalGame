package group6.animalgame.controllers;

import group6.animalgame.Main;
import group6.animalgame.logic.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class InitialController {

    Main main;
    FileChooser fileChooser = new FileChooser();

    @FXML
    Button newGameButton;
    @FXML
    Button loadGameButton;

    @FXML
    protected void onNewGameButtonClick() throws IOException {
        newGameButton.getScene().getWindow().setUserData(new Game(main));
        main.setScene("gameScene");
    }

    @FXML
    protected void onLoadGameButtonClick() {
        System.out.println("Load game button clicked!");
        File selectedFile = fileChooser.showOpenDialog(loadGameButton.getScene().getWindow());
    }

    @FXML
    protected void onQuitGameButtonClick() {
        System.exit(0);
    }

    public void initializeValues(Main main) {
        this.main = main;
    }
}
