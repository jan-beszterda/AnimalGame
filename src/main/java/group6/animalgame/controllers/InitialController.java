package group6.animalgame.controllers;

import group6.animalgame.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

public class InitialController {

    Main main;
    FileChooser fileChooser = new FileChooser();

    @FXML
    Button loadGameButton;

    @FXML
    protected void onNewGameButtonClick() {
        System.out.println("New game button clicked!");
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
