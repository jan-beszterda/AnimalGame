package group6.animalgame;

import group6.animalgame.controllers.InitialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class responsible for starting the game.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Main extends Application {

    private Stage mainWindow;
    private Scene initialScene, playerChoiceScene, buyFodderScene, chooseAmountScene, buyAnimalScene, mateAnimalsScene,
            sellAnimalsScene, endGameScene;
    private InitialController initialController;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        mainWindow = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("initial-view.fxml"));
        initialScene = new Scene(fxmlLoader.load());
        initialController = fxmlLoader.getController();
        initialController.initializeValues(this);
        stage.setTitle("Hello!");
        stage.setScene(initialScene);
        stage.show();
    }
}
