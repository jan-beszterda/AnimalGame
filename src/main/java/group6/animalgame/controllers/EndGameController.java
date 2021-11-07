package group6.animalgame.controllers;

import group6.animalgame.Main;
import group6.animalgame.logic.Game;
import group6.animalgame.logic.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class EndGameController {

    private Main main;
    private Game game;

    @FXML
    TextArea textArea;
    @FXML
    Button okButton;

    @FXML
    private void onOkButtonClick() {
        Platform.exit();
    }

    private String showResult(ArrayList<Player> players) {
        StringBuilder result = new StringBuilder();
        result.append("-".repeat(60)).append("\n");
        result.append("ALL ROUNDS PLAYED! GAME ENDS!\n");
        result.append("-".repeat(60)).append("\n");
        result.append("END RESULT:\n");
        for (Player player : players) {
            result.append(player.getName()).append(" (money: ").append(player.getMoney()).append(")\n");
        }
        result.append("-".repeat(60)).append("\n");
        for (int i = players.size() - 1; i > 0; i--) {
            if (players.get(i).getMoney() < players.get(0).getMoney()) {
                players.remove(i);
            }
        }
        if (players.size() > 1) {
            result.append("Game ended in a draw! The winners are:\n");
            for (Player winner : players) {
                result.append(winner.getName()).append("\n");
            }
        } else {
            result.append("The winner is ").append(players.get(0).getName());
        }
        return result.toString();
    }

    public void initializeValues(Main main) {
        this.main = main;
        if (game == null) {
            this.game = (Game) okButton.getScene().getWindow().getUserData();
        }
        textArea.setText(showResult(game.finalizeGame()));
    }
}
