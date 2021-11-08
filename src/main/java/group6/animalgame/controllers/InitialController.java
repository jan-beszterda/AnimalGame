package group6.animalgame.controllers;

import group6.animalgame.Main;
import group6.animalgame.logic.Game;
import group6.animalgame.utilities.FileUtilities;
import javafx.application.Platform;
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
    protected void onLoadGameButtonClick() throws IOException {
        File selectedFile = fileChooser.showOpenDialog(loadGameButton.getScene().getWindow());
        Game game = FileUtilities.loadGameFromFile(selectedFile);
        if (game != null) {
            game.setMain(main);
            loadGameButton.getScene().getWindow().setUserData(game);
            main.setScene("gameScene");
        }
    }

    @FXML
    protected void onQuitGameButtonClick() {
        Platform.exit();
    }

    public void initializeValues(Main main) {
        this.main = main;
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Save files", "*.ser"));
    }
}
