package group6.animalgame;

import group6.animalgame.controllers.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

/**
 * Main class responsible for starting the game.
 * @author Damir Kahvic, Malin Ovenmark, Jan Beszterda, Love Hillblom
 */
public class Main extends Application {

    private Stage mainWindow;
    private Scene initialScene, gameScene, buyAnimalsScene, buyFodderScene, feedAnimalScene, mateAnimalsScene,
            sellAnimalsScene, endGameScene;
    private InitialController initialController;
    GameController gameController;
    BuyAnimalsController buyAnimalsController;
    BuyFodderController buyFodderController;
    FeedAnimalController feedAnimalController;
    MateAnimalsController mateAnimalsController;
    SellAnimalsController sellAnimalsController;
    EndGameController endGameController;
    FXMLLoader fxmlLoader;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        mainWindow = stage;

        fxmlLoader = new FXMLLoader(Main.class.getResource("initial-view.fxml"));
        initialScene = new Scene(fxmlLoader.load());
        initialController = fxmlLoader.getController();
        initialController.initializeValues(this);

        stage.setTitle("Animal Game!");
        stage.setScene(initialScene);
        stage.setOnCloseRequest(windowEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Warning!");
            alert.setHeaderText("Warning! You're going to loose your game progress if you close the game now.");
            alert.setContentText("Confirm closing?");
            if (!stage.getScene().equals(initialScene) && !stage.getScene().equals(endGameScene)) {
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    Platform.exit();
                } else {
                    windowEvent.consume();
                }
            }
        });
        stage.show();
    }

    public void setScene(String sceneName) throws IOException {
        switch (sceneName) {
            case "gameScene":
                fxmlLoader = new FXMLLoader(Main.class.getResource("game-view.fxml"));
                gameScene = new Scene(fxmlLoader.load());
                gameController = fxmlLoader.getController();
                mainWindow.setScene(gameScene);
                gameController.initializeValues(this);
                break;
            case "buyAnimalsScene":
                fxmlLoader = new FXMLLoader(Main.class.getResource("buy-animals-view.fxml"));
                buyAnimalsScene = new Scene(fxmlLoader.load());
                buyAnimalsController = fxmlLoader.getController();
                mainWindow.setScene(buyAnimalsScene);
                buyAnimalsController.initializeValues(this);
                break;
            case "buyFodderScene":
                fxmlLoader = new FXMLLoader(Main.class.getResource("buy-fodder-view.fxml"));
                buyFodderScene = new Scene(fxmlLoader.load());
                buyFodderController = fxmlLoader.getController();
                mainWindow.setScene(buyFodderScene);
                buyFodderController.initializeValues(this);
                break;
            case "feedAnimalScene":
                fxmlLoader = new FXMLLoader(Main.class.getResource("feed-animal-view.fxml"));
                feedAnimalScene = new Scene(fxmlLoader.load());
                feedAnimalController = fxmlLoader.getController();
                mainWindow.setScene(feedAnimalScene);
                feedAnimalController.initializeValues(this);
                break;
            case "mateAnimalsScene":
                fxmlLoader = new FXMLLoader(Main.class.getResource("mate-animals-view.fxml"));
                mateAnimalsScene = new Scene(fxmlLoader.load());
                mateAnimalsController = fxmlLoader.getController();
                mainWindow.setScene(mateAnimalsScene);
                mateAnimalsController.initializeValues(this);
                break;
            case "sellAnimalsScene":
                fxmlLoader = new FXMLLoader(Main.class.getResource("sell-animals-view.fxml"));
                sellAnimalsScene = new Scene(fxmlLoader.load());
                sellAnimalsController = fxmlLoader.getController();
                mainWindow.setScene(sellAnimalsScene);
                sellAnimalsController.initializeValues(this);
                break;
            case "endGameScene":
                fxmlLoader = new FXMLLoader(Main.class.getResource("end-game-view.fxml"));
                endGameScene = new Scene(fxmlLoader.load());
                endGameController = fxmlLoader.getController();
                mainWindow.setScene(endGameScene);
                endGameController.initializeValues(this);
                break;
        }
        mainWindow.show();
    }
}
